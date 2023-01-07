package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object PingCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val target = handleTarget(sender, args, "We can't find the ping of an imaginary player ;c") ?: return true
        if (target == sender) sender.sendColoured("&pYour ping is &s${target.ping}ms")
        else sender.sendColoured("&p${target.name}'s ping is &#ffb5c${target.ping}ms")

        return true;
    }

}