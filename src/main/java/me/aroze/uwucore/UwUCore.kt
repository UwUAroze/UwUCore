package me.aroze.uwucore

import me.aroze.uwucore.command.*
import me.aroze.uwucore.listener.ChatListener
import me.aroze.uwucore.listener.PlayerListener
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {

    companion object {
        fun getInstance() : UwUCore { return getPlugin(UwUCore::class.java) }
    }

    override fun onEnable() {

        saveDefaultConfig()

        getCommand("gamemode")?.setExecutor(GamemodeCommand)
        getCommand("heal")?.setExecutor(HealCommand)
        getCommand("kittycannon")?.setExecutor(KittyCannonCommand)
        getCommand("feed")?.setExecutor(FeedCommand)
        getCommand("smite")?.setExecutor(SmiteCommand)
        getCommand("ping")?.setExecutor(PingCommand)
        getCommand("flyspeed")?.setExecutor(FlySpeedCommand)
        getCommand("walkspeed")?.setExecutor(WalkSpeedCommand)
        getCommand("uwuchat")?.setExecutor(UwUChatCommand)
        getCommand("fly")?.setExecutor(FlyCommand)

        server.pluginManager.registerEvents(PlayerListener, this)
        server.pluginManager.registerEvents(ChatListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}