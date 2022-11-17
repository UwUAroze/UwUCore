package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.handleTarget
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object PingCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val target = handleTarget(sender, args, "We can't find the ping of an imaginary player ;c") ?: return true
        if (target == sender) sender.sendMessage("&#ffd4e3Your ping is &#ffb5cf${target.ping}ms".coloured())
        else sender.sendMessage("&#ffd4e3${target.name}'s ping is &#ffb5c${target.ping}ms".coloured())

        return true;
    }

}