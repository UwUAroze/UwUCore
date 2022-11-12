package me.aroze.uwucore

import me.aroze.uwucore.command.GamemodeCommand
import me.aroze.uwucore.command.HealCommand
import me.aroze.uwucore.command.KittyCannonCommand
import me.aroze.uwucore.listener.PlayerListener
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {

    companion object {
        fun getInstance() : UwUCore { return getPlugin(UwUCore::class.java) }
    }

    override fun onEnable() {
        getCommand("gamemode")?.setExecutor(GamemodeCommand)
        getCommand("heal")?.setExecutor(HealCommand)
        getCommand("kittycannon")?.setExecutor(KittyCannonCommand)

        server.pluginManager.registerEvents(PlayerListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}