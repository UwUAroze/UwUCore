package me.aroze.uwucore

import me.aroze.uwucore.command.GamemodeCommand
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {
    override fun onEnable() {
        getCommand("gamemode")?.setExecutor(GamemodeCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}