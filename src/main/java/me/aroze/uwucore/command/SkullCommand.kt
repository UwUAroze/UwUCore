package me.aroze.uwucore.command

import kong.unirest.Unirest
import me.aroze.uwucore.UwUCore.Companion.getInstance
import me.aroze.uwucore.santa.async
import me.aroze.uwucore.util.*
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getServer
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.material.Skull

object SkullCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("skull")) return true
        val player = sender as Player

        async {

            var target : OfflinePlayer = if (args.isEmpty()) player
            else Bukkit.getOfflinePlayer(args[0])

            sender.sendColoured("&#ffd4e3Fetching skin...")

            val response = Unirest.get("https://api.ashcon.app/mojang/v2/user/${target.name}")
                .header("Content-Type", "application/json")
                .asJson()
                .body

            if (response.`object`.has("code")) {
                sender.isStupid("That player doesn't exist!")
                return@async
            }

            fetchSkull(target) {
                player.inventory.addItem(it)
                if (sender == target) sender.sendColoured("&#ffd4e3Look, it's your face!")
                else sender.sendColoured("&#ffd4e3You've received head from &#ffb5cf${target.name}&#ffd4e3... heh.")
            }

        }

        return true

    }

}