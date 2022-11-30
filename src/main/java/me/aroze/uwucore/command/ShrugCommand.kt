package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ShrugCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("shrug")) return true
        if (sender !is Player) return sender.isStupid("Shrugging is for humans only!")

        val message = args.joinToString(" ")
        if (message.isEmpty()) sender.chat("¯\\_(ツ)_/¯")
        else sender.chat("$message ¯\\_(ツ)_/¯")

        return true

    }

}