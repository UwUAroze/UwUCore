package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EnderChestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("enderchest", "Place one down yourself; and if you can't, uh, too bad!")) return true

        val target = handleTarget(sender, args, "We can only read enderchests of online players ;c", true) ?: return true

        val executor = sender as Player
        if (target != sender) {
            if (sender.isRightless("enderchest.others", "You aren't special enough to open other enderchests, ily though <3")) return true
            executor.sendColoured("&p&oIt's &s${target.name}&p&o's enderchest! I wonder what's inside...")
        } else target.sendColoured("&p&oEnsue enderchest creek...")

        executor.openInventory(target.enderChest)
        return true

    }

}