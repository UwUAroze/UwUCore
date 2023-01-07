package me.aroze.uwucore.command

import kong.unirest.Unirest
import me.aroze.uwucore.santa.async
import me.aroze.uwucore.util.*
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SkullCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("skull")) return true
        if (sender !is Player) return sender.isStupid("You need to be a player to receive head!")
        val player = sender as Player

        val typedCommand = label.removePrefix("uwucore:")

        if (args.size > 1) return sender.isStupid("That's too many args!")

        async {

            if (args.isNotEmpty() && args[0].length > 16) {

                if (validateTextureBase64(args[0], true)) {
                    player.inventory.addItem(getSkullFromBsae64(args[0]))
                    sender.sendFinalColoured("&pWe've generated a &s${typedCommand.lowercase()}&p from your &stexture string&p, :D")
                    return@async
                }

                if (validateTextureURL(args[0])) {
                    player.inventory.addItem(getSkullFromURL(args[0]))
                    sender.sendFinalColoured("&pWe've generated a &s${typedCommand.lowercase()}&p from your &stexture link&p, :D")
                    return@async
                }

                sender.isStupid("That's not a player name, a texture string or a valid link!")
                return@async
            }

            val target : OfflinePlayer = if (args.isEmpty()) player
            else Bukkit.getOfflinePlayer(args[0])

            sender.sendColoured("&pFetching skin...")

            val response = Unirest.get("https://api.mojang.com/users/profiles/minecraft/${target.name}")
                .header("Content-Type", "application/json")
                .asJson()
                .body

            if (response.`object`.isEmpty || response.`object`.has("error")) {
                sender.isStupid("That player doesn't exist!")
                return@async
            }

            fetchSkull(target) {
                player.inventory.addItem(it)
                if (sender == target) sender.sendColoured("&pLook, it's your face!")
                else sender.sendColoured("&pYou've received head from &s${target.name}&p... heh.")
            }

        }

        return true

    }

}