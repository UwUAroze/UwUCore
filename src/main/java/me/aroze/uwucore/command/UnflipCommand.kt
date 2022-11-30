package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object UnflipCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("unflip")) return true
        if (sender !is Player) return sender.isStupid("Unflipping tables is for humans only!")

        val message = args.joinToString(" ")
        if (message.isEmpty()) sender.chat("┬─┬ ノ( ゜-゜ノ)")
        else sender.chat("$message ¯┬─┬ ノ( ゜-゜ノ)")

        return true

    }

}