package me.aroze.uwucore.util

import me.aroze.uwucore.UwUCore
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

fun ItemStack.flag(vararg flags: String) {
    val meta = this.itemMeta ?: return
    for (flag in flags) meta.persistentDataContainer.set(key(flag), PersistentDataType.BYTE, 1.toByte())
    this.itemMeta = meta
}

fun ItemStack.isFlagged(vararg flags: String): Boolean {
    val meta = this.itemMeta ?: return false
    for (flag in flags) if (!meta.persistentDataContainer.has(key(flag), PersistentDataType.BYTE)) return false
    return true
}

private fun key(key: String): NamespacedKey {
    return NamespacedKey(UwUCore.getInstance(), key)
}