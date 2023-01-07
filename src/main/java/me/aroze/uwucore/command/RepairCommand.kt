package me.aroze.uwucore.command

import me.aroze.uwucore.util.*
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object RepairCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("repair")) return true
        if (sender !is Player && args.isEmpty()) return sender.isStupid("You aren't a player! So specify one, silly")

        var repairType = "hand"

        val target = when (args.size) {
            0 -> sender as Player
            1 -> {
                Bukkit.getPlayer(args[0]) ?: run {
                    repairType = args[0]
                    if (sender !is Player) return sender.isStupid("You aren't a player! So specify one, silly")
                    sender as Player
                }
            }
            2 -> {
                repairType = args[0].lowercase()
                Bukkit.getPlayer(args[1])
            }
            else -> return sender.isStupid("That's too many args! /repair [hand | all | *] [player]")
        } ?: return sender.isStupid("Does '${args[1]}' sound like a player? No.")

        if (target != sender && sender.isRightless("repair.others")) return sender.isStupid("You're only allowed to repair your own items!")

        when (repairType) {

            "hand" -> {
                if (!target.inventory.itemInMainHand.repair()) {
                    return if (sender == target) sender.isStupid("That item isn't damaged! Perhaps you meant /repair all?")
                    else sender.isStupid("That player's held item isn't damaged! Did you mean /repair all?")
                }
                else {
                    if (sender == target) sender.sendColoured("&pbing bang... your &sheld item&p has been repaired <3")
                    else {
                        sender.sendColoured("&pYou've repaired &s${target.name}'s&p held item, awh!")
                        target.sendColoured("&pSome mega cutie &srepaired&p your &sheld item&p, say thank you!")
                    }
                }
            }

            "all", "*" -> {
                val repairedItems = target.repairAllItems()
                if (repairedItems == 0) {
                    return if (sender == target) sender.isStupid("You don't have any damaged items &myou paranoid fuck")
                    else sender.isStupid("That player doesn't have any damaged items!")
                }
                if (sender == target) sender.sendColoured("&pbing bang... &s${repairedItems} ${"item".handlePluralChar(repairedItems)}&p in your inventory ${"has".handlePluralWord(repairedItems, "have")} been repaired <3")
                else {
                    sender.sendColoured("&pYou've repaired all of &s${target.name}'s&p items, awh!")
                    target.sendColoured("&pSome cutie &srepaired&p &sall items&p in your inventory, awh!")
                }
            }

            else -> return sender.isStupid("That's not a valid repair type! /repair [hand | all | *] [player]")
        }

        target.playSound(target.location, Sound.BLOCK_ANVIL_USE, 1F, 1F)
        if (sender != target && sender is Player) (sender as Player).playSound(target.location, Sound.BLOCK_ANVIL_USE, 1F, 1F)

        return true

    }

}