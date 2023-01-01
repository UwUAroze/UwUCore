package me.aroze.uwucore.util.type

class RGBColour(val red: Int, val green: Int, val blue: Int) {

    fun toHex(): String {
        return String.format("#%02x%02x%02x", red, green, blue)
    }

}