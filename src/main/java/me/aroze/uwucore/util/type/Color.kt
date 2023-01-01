package me.aroze.uwucore.util.type

class Color {

    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0

    companion object {
        @JvmStatic
        fun fromRGB(red: Int, green: Int, blue: Int): Color {
            return Color().apply {
                this.red = red
                this.green = green
                this.blue = blue
            }
        }

        @JvmStatic
        fun fromHex(hex: String): Color {
            return Color().apply {
                this.red = hex.substring(0, 2).toInt(16)
                this.green = hex.substring(2, 4).toInt(16)
                this.blue = hex.substring(4, 6).toInt(16)
            }
        }
    }

    fun toHex(): String {
        return "#${red.toString(16)}${green.toString(16)}${blue.toString(16)}"
    }

    fun red() = red
    fun green() = green
    fun blue() = blue

}