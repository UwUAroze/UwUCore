package me.aroze.uwucore.command

import me.aroze.uwucore.UwUCore.Companion.getInstance
import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import me.aroze.uwucore.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object FlyCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("fly", getInstance().config.getString("messages.permission.fly")!!)) return true

        if (args.isNotEmpty() && sender.isRightless("fly.others", "You're only allowed to toggle your own flight!")) return true
        val target = handleTarget(sender, args, "We can't toggle an imaginary player's flight ;c") ?: return true

        target.allowFlight = !target.allowFlight

        if (target.allowFlight) {
            if (target === sender) return sender.sendFinalColoured("&#ffd4e3You've given yourself superpowers ;o. &#ffb5cf&mgo away!&#ffb5cf fly away!")
            target.sendColoured("&#ffd4e3A kind soul has given you &#ffb5cfthe ability to fly. &#ffd4e3Say thank you! smh")
            return sender.sendFinalColoured("&#ffd4e3You've &#ffb5cfenabled &#ffd4e3flight for &#ffb5cf${target.name}. &#ffd4e3how nice of you.")
        }

        if (target == sender) return sender.sendFinalColoured("&#ffd4e3You've &#ffb5cfdisabled flight &#ffd4e3&o...why? you're so confusing.")
        sender.sendColoured("&#ffd4e3Your meanie ass has &#ffb5cfdisabled &#ffd4e3flight for &#ffb5cf${target.name} ;c")
        return target.sendFinalColoured("&#ffd4e3You have been stripped of your &#ffb5cfflying abilities &#ffd4e3;c")
    }

}