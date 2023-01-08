package me.aroze.uwucore.command

import me.aroze.uwucore.util.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


object HatCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return true

        if (sender.isRightless("hat")) return true

        val playerTool: ItemStack = sender.inventory.itemInMainHand
        val helmet: ItemStack? = sender.inventory.helmet

        val itemSlot = sender.inventory.heldItemSlot

        sender.inventory.setItem(itemSlot, helmet)
        sender.inventory.helmet = playerTool

        if (playerTool.type.isAir) {
            if (helmet == null) return sender.isStupid("We don't want to overdose you on oxygen!")
            return sender.sendFinalColoured("&p&oknocks off your hat as you stare... \"oops\"")
        } else return sender.sendFinalColoured("&pEnjoy your new hat!")

    }

}