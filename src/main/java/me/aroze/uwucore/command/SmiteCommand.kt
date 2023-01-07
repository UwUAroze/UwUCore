package me.aroze.uwucore.command

import me.aroze.uwucore.util.handleTarget
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object SmiteCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("smite")) return true

        val target = handleTarget(sender, args) ?: return true
        var smiteLocation = target.location

        if (args.isEmpty()) {
            smiteLocation = target.getTargetBlock(null, 100).location
            if (smiteLocation.block.isEmpty) return sender.isStupid("We can't smite the air ;c")
            sender.sendColoured("&pboom.")
        }

        if (sender != target) {
            sender.sendColoured("&pPoor &#eb9bb7${target.name}&p, ;c")
            target.sendColoured("&pThat must have hurt.")
        } else if (args.isNotEmpty()) sender.sendColoured("&pSelf harm &#eb9bb7isn't the answer &p...&mbut i'll let you do it anyways :3")

        smiteLocation.world!!.strikeLightning(smiteLocation)

        return true
    }

}