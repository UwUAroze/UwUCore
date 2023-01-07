package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendColoured
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GamemodeCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("gamemode")) return true

        var gamemode : String;
        var player = sender as Player?;
        val typedCommand = label.lowercase().removePrefix("uwucore:"); // "gamemode" or "gm" or gms/gmc/etc

        if (typedCommand != "gamemode" && typedCommand != "gm") {
            if (args.size == 1) player = Bukkit.getPlayer(args[0])
            else if (args.size > 1) return sender.isStupid("You dummy. /$typedCommand OR /$typedCommand <player>. nothing else weirdo")
        }

        when (typedCommand) {
            "gms", "gm0" -> gamemode = "survival"
            "gmc", "gm1" -> gamemode = "creative"
            "gma", "gm2" -> gamemode = "adventure"
            "gmsp", "gmspec", "gm3" -> gamemode = "spectator"

            else -> {
                if (args.isEmpty()) return sender.isStupid("You know how /gamemode needs args? Yeah, add them idiot.")
                if (args.size > 2) return sender.isStupid("Psst! /gamemode <gamemode> [player]")

                if (args.size == 2) player = Bukkit.getPlayer(args[1])
                gamemode = args[0].lowercase()
            }
        }

        if (player == null) return sender.isStupid("We cannot alter the gamemode of an imaginary player ;c")

        val oldGamemode = player.gameMode
        if (!setGamemode(player, gamemode)) return sender.isStupid("Does \"$gamemode\" sound like a gamemode? No.")

        val formattedGamemode = player.gameMode.toString().lowercase()
        val name = player.name

        if (oldGamemode == player.gameMode) {
            if (player == sender) sender.sendColoured("&pYour gamemode is already set to &s$formattedGamemode&p, silly")
            else sender.sendColoured("&p$name's gamemode is already set to &s$formattedGamemode&p, silly")
            return true
        }

        player.sendColoured("&pYour gamemode has been set to &s$formattedGamemode")
        if (player != sender) sender.sendColoured("&pYou have set &s$name&p's gamemode to &s$formattedGamemode")

        return true

    }

    @Suppress("DEPRECATION")
    private fun setGamemode(player: Player, query: String) : Boolean {
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