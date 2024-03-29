package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.flag
import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object KittyCannonCommand : CommandExecutor {

    private val kittyCannon = ItemStack(Material.END_ROD)

    init {
        val kittyCannonMeta: ItemMeta = kittyCannon.itemMeta!!
        kittyCannonMeta.setDisplayName("&#ffa1b5Kitty Abusey 1000".coloured())
        kittyCannon.itemMeta = kittyCannonMeta
        kittyCannon.flag("kittyAbuser")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("kitty", "No kitty for you!")) return true
        if (sender !is Player) sender.isStupid("You aren't a player, go away ;c")

        val player = sender as Player
        player.inventory.addItem(kittyCannon)

        return true;
    }

}