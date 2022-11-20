package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object WhooshCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("fling")) return true
        val target = handleTarget(sender, args, "We can't fling an imaginary player ;c") ?: return true

        target.velocity = target.velocity.add(target.location.direction).setY(0.7).multiply(2.5)

        if (sender != target) sender.sendMessage("&#ffd4e3☁ You've whooshed &#ffb5cf${target.name} &#ffd4e3☁".coloured())
        target.sendMessage("&#ffd4e3Whoosh... ☁".coloured())

        return true
    }

}