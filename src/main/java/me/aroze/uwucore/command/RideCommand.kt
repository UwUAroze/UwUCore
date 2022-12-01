package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object RideCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("ride")) return true
        if (sender !is Player) return sender.isStupid("Only players can ride each other ;c")
        if (args.isEmpty()) return sender.isStupid("You need to specify a player!")
        if (args.size > 1) return sender.isStupid("Whoa there, too many args.")

        val target = sender.server.getPlayer(args[0])
            ?: return sender.isStupid("You can't ride someone who isn't here!")
        if (sender == target) return sender.isStupid("You can't ride yourself, &mas much as I know you'd like to")


        target.addPassenger(sender)

        sender.sendColoured("&#ffd4e3You are now riding &#ffb5cf${target.name} &#ffd4e3;)")

        return true

    }

}