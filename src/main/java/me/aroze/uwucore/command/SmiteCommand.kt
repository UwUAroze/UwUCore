package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.handleTarget
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object SmiteCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (!sender.hasPermission("uwucore.smite")) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6elol u wish".coloured())
            return true
        }

        val target = handleTarget(sender, args) ?: return true
        var smiteLocation = target.location

        if (args.isEmpty()) {
            smiteLocation = target.getTargetBlock(null, 100).location
            sender.sendMessage("&#ffd4e3boom.".coloured())
        }

        if (sender != target) {
            sender.sendMessage("&#ffd4e3Poor &#eb9bb7${target.name}&#ffd4e3, ;c".coloured())
            target.sendMessage("&#ffd4e3That must have hurt.".coloured())
        } else if (!args.isEmpty()) sender.sendMessage("&#ffd4e3Self harm &#eb9bb7isn't the answer &#ffd4e3...&mbut i'll let you do it anyways :3".coloured())

        smiteLocation.world!!.strikeLightning(smiteLocation)

        return true
    }

}