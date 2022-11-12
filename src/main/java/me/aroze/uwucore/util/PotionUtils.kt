package me.aroze.uwucore.util

import org.bukkit.potion.PotionEffect

fun PotionEffect.isHarmful() : Boolean {
    return when (type.name.uppercase()) {
        "BLINDNESS", "CONFUSION", "HARM", "HUNGER", "POISON", "SLOW", "SLOW_DIGGING", "WEAKNESS", "WITHER" -> true
        else -> false
    }
}