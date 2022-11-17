package me.aroze.uwucore.util

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Commonly found target logic for basic commands, heal, fly, feed, etc:
// - If no args are specified, target is sender. Else target is first arg
// - Sender can't be console with no args
// - Target must be online

fun handleTarget(sender: CommandSender, args: Array<out String>) : Player? {
    if (args.isEmpty()) {
        if (sender is Player) return sender as Player
        sender.sendMessage("&#ff6e6e⚠ &#ff7f6eYou aren't a player! So specify one, silly".coloured()); return null
    }
    if (Bukkit.getPlayer(args[0]) == null) sender.sendMessage("&#ff6e6e⚠ &#ff7f6eThat player doesn't exist, dummy".coloured())
    return Bukkit.getPlayer(args[0])
}

// Used for incorrect syntaxes/etc; generic error message format.
fun CommandSender.isStupid(message: String) : Boolean {
    this.sendMessage("&#ff6e6e⚠ &#ff7f6e$message".coloured())
    return true
}

// Permission check
// Should be used like: if (sender.isRightless("smite")) return true
fun CommandSender.isRightless(permission: String, message: String = "lol u wish") : Boolean {
    if (this.hasPermission("uwucore.$permission")) return false
    return this.isStupid(message)
}