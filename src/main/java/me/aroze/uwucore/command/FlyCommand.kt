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
            if (target === sender) return sender.sendFinalColoured("&pYou've given yourself superpowers ;o. &s&mgo away!&s fly away!")
            target.sendColoured("&pA kind soul has given you &sthe ability to fly. &pSay thank you! smh")
            return sender.sendFinalColoured("&pYou've &senabled &pflight for &s${target.name}. &phow nice of you.")
        }

        if (target == sender) return sender.sendFinalColoured("&pYou've &sdisabled flight &p&o...why? you're so confusing.")
        sender.sendColoured("&pYour meanie ass has &sdisabled &pflight for &s${target.name} ;c")
        return target.sendFinalColoured("&pYou have been stripped of your &sflying abilities &p;c")
    }

}