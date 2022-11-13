package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isHarmful
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object HealCommand : CommandExecutor{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (!sender.hasPermission("uwucore.heal")) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6elol u wish".coloured())
            return true
        }

        val player = handleTarget(sender, args) ?: return true
        val soft = if (label.contains("s")) " soft" else ""

        if (player !== sender) {
            val healer = if (sender is Player) sender.name else "console"
            sender.sendMessage("&#ffd4e3You've$soft healed &#eb9bb7${player.name}&#ffd4e3, aww.".coloured())
            player.sendMessage("&#ffd4e3You've been$soft healed by &#eb9bb7${healer}&#ffd4e3, how cute ;3".coloured())
        } else player.sendMessage("&#ffd4e3You've majestically$soft healed yourself".coloured())

        // "Soft heal": doesn't feed, remove fire or remove potion effects.
        player.health = 20.0
        player.foodLevel = 20
        if (soft != "") return true

        for (potion in player.activePotionEffects.filter { it.isHarmful() }) player.removePotionEffect(potion.type)
        player.fireTicks = 0
        return true
    }

}