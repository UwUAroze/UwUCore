package me.aroze.uwucore.util

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import kong.unirest.Unirest
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field
import java.util.*

fun fetchSkull(offlinePlayer: OfflinePlayer, getSkull: (ItemStack) -> Unit) {
    val skull = ItemStack(Material.PLAYER_HEAD)
    val meta = skull.itemMeta as SkullMeta
    meta.owningPlayer = offlinePlayer
    skull.itemMeta = meta
    getSkull.invoke(skull)
}

fun getSkullFromBsae64(base64: String) : ItemStack {

    val skull = ItemStack(Material.PLAYER_HEAD)
    val meta = skull.itemMeta as SkullMeta

    val profile = GameProfile(UUID.randomUUID(), null);
    profile.properties.put("textures", Property("textures", base64));

    val field : Field = meta.javaClass.getDeclaredField("profile")
    field.isAccessible = true
    field.set(meta, profile)

    skull.itemMeta = meta

    return skull

}

fun getSkullFromURL(url: String) : ItemStack {

    val b64 = Base64.getEncoder().encodeToString("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/${stripTextureForApi(url)}\"}}}".toByteArray())
    return getSkullFromBsae64(b64)

}

fun validateTextureURL(url: String) : Boolean {

    if (!"(?:https?:\\/\\/)?textures\\.minecraft\\.net\\/texture\\/(.+)".toRegex().matches(url)) return false
    return (validateTextureFromApi(url))

}

fun validateTextureBase64(textureString: String, checkTexturesApi: Boolean) : Boolean {
    val b64 : String

    try { b64 = String(Base64.getDecoder().decode(textureString)).replace("\"", "") }
    catch (e: Exception) { return false }

    val validB64 = (b64.startsWith("{textures:{SKIN:{url:http://textures.minecraft.net/texture/")
        && b64.endsWith("}}}"))

    if (!checkTexturesApi) return validB64
    
    return validateTextureFromApi(b64)
}

fun validateTextureFromApi(texture: String) : Boolean {

    val responseCode : Int
    try {
        responseCode = Unirest.get("https://textures.minecraft.net/texture/${stripTextureForApi(texture)}")
            .header("Content-Type", "application/json")
            .asJson()
            .status
    } catch (e: Exception) { return false }

    return responseCode == 200

}

fun stripTextureForApi(texture: String) : String {
    return texture
        .replace("\"", "")
        .replace("https", "http")
        .removePrefix("{textures:{SKIN:{url:")
        .removePrefix("http://")
        .removePrefix("textures.minecraft.net/texture/")
        .removeSuffix("}}}")
}