package me.aroze.uwucore.util

import me.santio.utils.color.Color
import net.md_5.bungee.api.ChatColor
import java.util.regex.Matcher
import java.util.regex.Pattern


val hexPattern = Pattern.compile("&(#[a-fA-F\\d]{6})");

enum class ChatColors(val hex: String) {
    PRIMARY("&#ffd4e3"),
    SECONDARY("&#ffb5cf"),
}

fun String.coloured(): String {
    var coloured = this
        .replace("&p", ChatColors.PRIMARY.hex)
        .replace("&s", ChatColors.SECONDARY.hex)

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

fun String.replaceCaseInsensitive(text: String, replacement: String): String {
    return this.replace(Regex("(?i)$text"), replacement)
}

fun String.handlePluralChar(amount: Int, plural: String = "s"): String {
    return if (amount == 1) this else this + plural
}

fun String.handlePluralWord(amount: Int, plural: String): String {
    return if (amount == 1) this else plural
}

fun Color.toMinecraft() : String = "&${this.toHex()}"

fun String.gradient(start: Color, end: Color): String {

    val length = this.length

    val startRed = start.red
    val startGreen = start.green
    val startBlue = start.blue

    val endRed = end.red
    val endGreen = end.green
    val endBlue = end.blue

    val stepRed = (endRed - startRed) / length
    val stepGreen = (endGreen - startGreen) / length
    val stepBlue = (endBlue - startBlue) / length

    var red = startRed
    var green = startGreen
    var blue = startBlue

    val builder = StringBuilder()
    while (builder.length < length) {
        builder.append(Color.fromRGB(red, green, blue).toMinecraft() + this[builder.length])
        red += stepRed
        green += stepGreen
        blue += stepBlue
    }

    return builder.toString()

}