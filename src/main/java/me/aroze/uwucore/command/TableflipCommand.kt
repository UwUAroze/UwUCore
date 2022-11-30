package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TableflipCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("tableflip")) return true
        if (sender !is Player) return sender.isStupid("Flipping tables is for humans only!")

        val message = args.joinToString(" ")
        if (message.isEmpty()) sender.chat("(╯°□°）╯︵ ┻━┻")
        else sender.chat("$message ¯(╯°□°）╯︵ ┻━┻")

        return true

    }

}