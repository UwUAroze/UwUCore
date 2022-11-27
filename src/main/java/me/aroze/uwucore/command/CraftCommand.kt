package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import me.aroze.uwucore.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object CraftCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("craft")) return true
        if (args.isNotEmpty() && sender.isRightless("craft.others", "That crafting table is only for you, sharing isn't allowed!")) return true

        val target = handleTarget(sender, args) ?: return true
        target.openWorkbench(null, true)

        return if (sender != target) {
            sender.sendColoured("&#ffd4e3You've opened a crafting table for &#eb9bb7${target.name}&#ffd4e3, awhh")
            target.sendFinalColoured("&#ffd4e3Some cutie opened a crafting table for you ;3")
        } else target.sendFinalColoured("&#ffd4e3Lazy ass.")

    }

}