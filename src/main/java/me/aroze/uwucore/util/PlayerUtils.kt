package me.aroze.uwucore.util

import org.bukkit.entity.Player

fun Player.repairAllItems() : Int {
    var repairedItems = 0
    this.inventory.contents.mapNotNull{ it }.forEach{ if (it.repair()) repairedItems++ }
    return repairedItems
}