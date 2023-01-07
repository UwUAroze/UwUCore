package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isHarmful
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object HealCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("heal")) return true

        val player = handleTarget(sender, args) ?: return true
        val soft = if (label.contains("s")) " soft" else ""

        if (player !== sender) {
            val healer = if (sender is Player) sender.name else "console"
            sender.sendColoured("&pYou've$soft healed &#eb9bb7${player.name}&p, aww.")
            player.sendColoured("&pYou've been$soft healed by &#eb9bb7${healer}&p, how cute ;3")
        } else player.sendColoured("&pYou've majestically$soft healed yourself")

        // "Soft heal": doesn't feed, remove fire or remove potion effects.
        player.health = 20.0
        player.foodLevel = 20
        if (soft != "") return true

        for (potion in player.activePotionEffects.filter { it.isHarmful() }) player.removePotionEffect(potion.type)
        player.fireTicks = 0
        return true
    }

}