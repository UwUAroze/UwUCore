package me.aroze.uwucore.command

import me.aroze.uwucore.util.coloured
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GamemodeCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return false
        if (!sender.hasPermission("uwucore.gamemode")) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6elol u wish".coloured())
            return true
        }

        var gamemode : String;
        var player = sender as Player?;
        val typedCommand = label.lowercase().removePrefix("uwucore:"); // "gamemode" or "gm" or gms/gmc/etc

        if (typedCommand != "gamemode" && typedCommand != "gm") {
            if (args.size == 1) player = Bukkit.getPlayer(args[0])
            else if (args.size > 1) {
                sender.sendMessage("&#ff6e6e⚠ &#ff7f6eYou dummy. /$typedCommand OR /$typedCommand <player>. nothing else weirdo".coloured())
                return true
            }
        }

        when (typedCommand) {
            "gms", "gm0" -> gamemode = "survival"
            "gmc", "gm1" -> gamemode = "creative"
            "gma", "gm2" -> gamemode = "adventure"
            "gmsp", "gmspec", "gm3" -> gamemode = "spectator"

            else -> {
                if (args.isEmpty()) { sender.sendMessage("&#ff6e6e⚠ &#ff7f6eYou know how /gamemode needs args? Yeah, add them idiot.".coloured()); return true; }
                if (args.size > 2) { sender.sendMessage("&#ff6e6e⚠ &#ff7f6ePsst! /gamemode <gamemode> [player]".coloured()); return true; }

                if (args.size == 2) player = Bukkit.getPlayer(args[1])
                gamemode = args[0].lowercase()
            }
        }

        if (player == null) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6eWe cannot alter the gamemode of an imaginary player ;c".coloured())
            return true
        }

        val oldGamemode = player.gameMode

        if (!setGamemode(player, gamemode)) {
            sender.sendMessage("&#ff6e6e⚠ &#ff7f6eDoes \"$gamemode\" sound like a gamemode? No.".coloured())
            return true
        }

        val formattedGamemode = player.gameMode.toString().lowercase()
        val name = player.name

        if (oldGamemode == player.gameMode) {
            if (player == sender) sender.sendMessage("&#ffd4e3Your gamemode is already set to &#ffb5cf$formattedGamemode&#ffd4e3, silly".coloured())
            else sender.sendMessage("&#ffd4e3$name's gamemode is already set to &#ffb5cf$formattedGamemode&#ffd4e3, silly".coloured())
            return true
        }

        player.sendMessage("&#ffd4e3Your gamemode has been set to &#ffb5cf$formattedGamemode".coloured())
        if (player != sender) sender.sendMessage("&#ffd4e3You have set &#ffb5cf$name&#ffd4e3's gamemode to &#ffb5cf$formattedGamemode".coloured())

        return true

    }

    @Suppress("DEPRECATION")
    fun setGamemode(player: Player, query: String) : Boolean {
        val gamemode : GameMode = if (query.toIntOrNull() != null) GameMode.getByValue(query.toInt())
        else {
            GameMode.values().find {
                it.name.lowercase().startsWith(query.lowercase())
            }
        } ?: return false;

        player.gameMode = gamemode
        return true;
    }

}