package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object SudoCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("sudo")) return true

        if (args.isEmpty()) return sender.isStupid("You need to specify a player to sudo!")
        if (args.size == 1) return sender.isStupid("You need to specify a chat message/command!")

        val target = sender.server.getPlayer(args[0]) ?: return sender.isStupid("We can't sudo an imaginary player ;c")
        if (target == sender) return sender.isStupid("That seems like a waste of my time, do it yourself.")

        val message = args.drop(1).joinToString(" ")

        target.chat(message)

        return if (message.startsWith("/")) sender.sendFinalColoured("&#ffd4e3We've forcefully made &#eb9bb7${target.name}&#ffd4e3 execute your command, :D")
        else sender.sendFinalColoured("&#ffd4e3We've forcefully made &#eb9bb7${target.name}&#ffd4e3 type your message, :D")

    }

}