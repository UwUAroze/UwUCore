package me.aroze.uwucore.util

import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

fun fetchSkull(offlinePlayer: OfflinePlayer, getSkull: (ItemStack) -> Unit) {
    val skull = ItemStack(Material.PLAYER_HEAD)
    val meta = skull.itemMeta as SkullMeta
    meta.owningPlayer = offlinePlayer
    skull.itemMeta = meta
    getSkull.invoke(skull)
}