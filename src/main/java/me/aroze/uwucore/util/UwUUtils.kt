package me.aroze.uwucore.util

import kotlin.math.ceil

fun uwuify(text: String) : String {

    var toReturn = text

    toReturn.replaceCaseInsensitive("\\. ", "~ ");
    toReturn.replaceCaseInsensitive(", ", "~ ");
    toReturn.replaceCaseInsensitive("- ", "~ ");
    toReturn.replaceCaseInsensitive("\\? ", "~ ");
    toReturn.replaceCaseInsensitive("hurt", "hUWUrt");
    toReturn.replaceCaseInsensitive("kill", "hwuwrt");
    toReturn.replaceCaseInsensitive("you", "you<3");
    toReturn.replaceCaseInsensitive("r", "w");
    toReturn.replaceCaseInsensitive("l", "w");
    toReturn.replaceCaseInsensitive("uwu", "UWU");
    toReturn.replaceCaseInsensitive("owo", "OWO");
    toReturn.replaceCaseInsensitive(";-;", "(-_-)");
    toReturn.replaceCaseInsensitive("-_-", "(-_-)");
    toReturn.replaceCaseInsensitive(":o", "※(^o^)/※");
    toReturn.replaceCaseInsensitive(":0", "※(^o^)/※");
    toReturn.replaceCaseInsensitive(":\\)", "(｡◕‿‿◕｡)");
    toReturn.replaceCaseInsensitive(":>", "(｡◕‿‿◕｡)");
    toReturn.replaceCaseInsensitive(":\\(", "(︶︹︶)");
    toReturn.replaceCaseInsensitive(":<", "(︶︹︶)");
    toReturn.replaceCaseInsensitive(":3", "(・3・)");
    toReturn.replaceCaseInsensitive(":D", "(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧");
    toReturn.replaceCaseInsensitive("\\._\\.", "(っ´ω`c)");
    toReturn.replaceCaseInsensitive("fuck", "fwick");
    toReturn.replaceCaseInsensitive("shit", "*poops*");
    toReturn.replaceCaseInsensitive("wtf", "whawt the fwick");
    toReturn.replaceCaseInsensitive("wth", "whawt the hecc");

    when ((ceil(Math.random() * 25)).toInt()) {
        1 -> toReturn += "~ uwu *nuzzles you*"
        2 -> toReturn += "~ owo whats this"
        3 -> toReturn += "~ *kisses you*"
        4 -> toReturn += "~ *blushes*"
        5 -> toReturn += "~ *hehe*"
        6 -> toReturn += "~ meow"
        7 -> toReturn += "~ owo"
        8 -> toReturn += "~ uwu"
        9 -> toReturn += " ;3"
        10 -> toReturn += "~"
    }

    return toReturn
}