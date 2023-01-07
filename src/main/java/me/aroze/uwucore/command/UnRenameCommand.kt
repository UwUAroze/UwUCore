package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object UnRenameCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("rename")) return true
        if (sender !is Player) return sender.isStupid("You aren't a player!")

        val item = sender.inventory.itemInMainHand
        val meta = item.itemMeta!!

        if (item.type.isAir) return sender.isStupid("Hold an item dumbass")
        if (meta.displayName == "") return sender.isStupid("This item isn't renamed, you fool")

        meta.setDisplayName("")
        item.itemMeta = meta

        return sender.sendFinalColoured("&pI've un-renamed your item, you're welcome.")

    }

}