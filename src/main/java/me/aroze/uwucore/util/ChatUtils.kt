package me.aroze.uwucore.util

import net.md_5.bungee.api.ChatColor
import java.util.regex.Matcher
import java.util.regex.Pattern


val hexPattern = Pattern.compile("&(#[a-fA-F\\d]{6})");

fun String.coloured(): String {
    var coloured = this
    var match: Matcher = hexPattern.matcher(coloured)
    while (match.find()) {
        val color: String = coloured.substring(match.start(), match.end())
        coloured = coloured.replace(color, ChatColor.of(color.substring(1)).toString())
        match = hexPattern.matcher(coloured)
    }
    return ChatColor.translateAlternateColorCodes('&', coloured)
}

fun String.undress(): String {
    return ChatColor.stripColor(this)
}