package me.aroze.uwucore.util

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

fun ItemStack.repair() : Boolean {
    if (!this.isDamaged()) return false
    val meta = this.itemMeta as Damageable
    meta.damage = 0
    this.itemMeta = meta
    return true
}

fun ItemStack.isDamaged() : Boolean {
    if (this.itemMeta !is Damageable) return false
    return ((this.itemMeta as Damageable).damage != 0)
}