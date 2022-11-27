package me.aroze.uwucore.util

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import kong.unirest.Unirest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.lang.Exception
import java.lang.reflect.Field
import java.util.*

fun fetchSkull(offlinePlayer: OfflinePlayer, getSkull: (ItemStack) -> Unit) {
    val skull = ItemStack(Material.PLAYER_HEAD)
    val meta = skull.itemMeta as SkullMeta
    meta.owningPlayer = offlinePlayer
    skull.itemMeta = meta
    getSkull.invoke(skull)
}

fun getSkullFromTexture(textureString: String) : ItemStack {

    val skull = ItemStack(Material.PLAYER_HEAD)
    val meta = skull.itemMeta as SkullMeta

    val profile = GameProfile(UUID.randomUUID(), null);
    profile.properties.put("textures", Property("textures", textureString));

    val field : Field = meta.javaClass.getDeclaredField("profile")
    field.isAccessible = true
    field.set(meta, profile)

    skull.itemMeta = meta

    return skull

}

fun validateTextureString(textureString: String, checkTexturesApi: Boolean) : Boolean {
    val b64 : String

    try { b64 = String(Base64.getDecoder().decode(textureString)).replace("\"", "") }
    catch (e: Exception) { return false }

    Bukkit.broadcastMessage(b64)

    val validB64 = (b64.startsWith("{textures:{SKIN:{url:http://textures.minecraft.net/texture/")
        && b64.endsWith("}}}"))

    if (!checkTexturesApi) return validB64
    
    return validateTextureFromApi(b64)
}

fun validateTextureFromApi(texture: String) : Boolean {

    val strippedTexture = texture
        .replace("\"", "")
        .removePrefix("{textures:{SKIN:{url:http://textures.minecraft.net/texture/")
        .removeSuffix("}}}")

    val responseCode : Int
    try {
        responseCode = Unirest.get("https://textures.minecraft.net/texture/${strippedTexture}")
            .header("Content-Type", "application/json")
            .asJson()
            .status
    } catch (e: Exception) { return false }

    return responseCode == 200

}