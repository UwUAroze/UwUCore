package me.aroze.uwucore

import me.aroze.uwucore.command.GamemodeCommand
import me.aroze.uwucore.command.HealCommand
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {
    override fun onEnable() {
        getCommand("gamemode")?.setExecutor(GamemodeCommand)
        getCommand("heal")?.setExecutor(HealCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}