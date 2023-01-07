package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object WhooshCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("fling")) return true
        val target = handleTarget(sender, args, "We can't fling an imaginary player ;c") ?: return true

        target.velocity = target.velocity.add(target.location.direction).setY(0.7).multiply(2.5)

        if (sender != target) sender.sendColoured("&p☁ You've whooshed &s${target.name} &p☁")
        target.sendColoured("&pWhoosh... ☁")

        return true
    }

}