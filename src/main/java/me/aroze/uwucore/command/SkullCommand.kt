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
import org.apache.commons.codec.binary.Base64

object SkullCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("skull")) return true
        if (sender !is Player) return sender.isStupid("You need to be a player to receive head!")
        val player = sender as Player

        val typedCommand = label.removePrefix("uwucore:")

        async {

            if (args.isNotEmpty() && args[0].length > 16) {

                if (args.isNotEmpty() && validateTextureString(args[0], true)) {
                    player.inventory.addItem(getSkullFromTexture(args[0]))
                    sender.sendFinalColoured("&#ffd4e3We've generated a &#ffb5cf${typedCommand.lowercase()}&#ffd4e3 from your &#ffb5cftexture string&#ffd4e3, :D")
                    return@async
                }

                sender.isStupid("That's not a player name, a texture skin or a valid link!")
                return@async
            }

            val target : OfflinePlayer = if (args.isEmpty()) player
            else Bukkit.getOfflinePlayer(args[0])

            sender.sendColoured("&#ffd4e3Fetching skin...")

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
                if (sender == target) sender.sendColoured("&#ffd4e3Look, it's your face!")
                else sender.sendColoured("&#ffd4e3You've received head from &#ffb5cf${target.name}&#ffd4e3... heh.")
            }

        }

        return true

    }

}