package me.aroze.uwucore.util

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
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