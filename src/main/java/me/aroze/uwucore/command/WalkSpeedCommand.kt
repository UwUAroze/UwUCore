package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WalkSpeedCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("walkspeed")) return true

        var player : Player? = null
        var speed = 1.0F

        if (sender !is Player && args.size <= 1) return sender.isStupid("You aren't a player, so specify one, along with a speed.")

        if (args.size == 1) {
            try { speed = args[0].toFloat() }
            catch (e: NumberFormatException) { return sender.isStupid("You must specify a number for the speed.") }
            player = sender as Player
        }

        if (args.size == 2) {
            if (sender.isRightless("walkspeed.others")) return true
            try { speed = args[0].toFloat() }
            catch (e: NumberFormatException) { return sender.isStupid("/ws <speed> [player]") }
            player = Bukkit.getPlayer(args[1])
        }

        if (args.size > 2) return sender.isStupid("Too many arguments!")
        if (player == null) return sender.isStupid("We can't set the walk speed of an imaginary player ;c")
        if (speed < 0 || speed > 10) return sender.isStupid("Walk speed must be a multiplier value between 0 and 10");

        player.walkSpeed = speed/10
        player.sendMessage("&#ffd4e3Your walk speed has been set to &#ffb5cf${speed}x".coloured())
        if (player != sender) sender.sendMessage("&#ffd4e3You've set ${player.name}'s walk speed to &#ffb5cf${speed}x".coloured())

        return true;

    }

}