package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import me.aroze.uwucore.util.flag
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object KittyCannonCommand : CommandExecutor {

    val kittyCannon = ItemStack(Material.END_ROD)

    init {
        val kittyCannonMeta: ItemMeta = kittyCannon.itemMeta!!
        kittyCannonMeta.setDisplayName("&#ffa1b5Kitty Abusey 1000".coloured())
        kittyCannon.itemMeta = kittyCannonMeta
        kittyCannon.flag("kittyAbuser")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (!sender.hasPermission("uwucore.kitty")) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6eNo kitty for you!".coloured())
            return true
        }

        if (sender !is Player) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6eYou aren't a player, go away ;c".coloured())
            return true
        }

        val player = sender as Player
        player.inventory.addItem(kittyCannon)

        return true;
    }

}