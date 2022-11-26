package me.aroze.uwucore

import me.aroze.uwucore.command.*
import me.aroze.uwucore.listener.ChatListener
import me.aroze.uwucore.listener.PlayerListener
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {

    companion object {
        fun getInstance() : UwUCore { return getPlugin(UwUCore::class.java) }
    }

    override fun onEnable() {

        saveDefaultConfig()

        addCommand("gamemode", GamemodeCommand)
        addCommand("heal", HealCommand)
        addCommand("kittycannon", KittyCannonCommand)
        addCommand("feed", FeedCommand)
        addCommand("smite", SmiteCommand)
        addCommand("ping", PingCommand)
        addCommand("flyspeed", FlySpeedCommand)
        addCommand("walkspeed", WalkSpeedCommand)
        addCommand("uwuchat", UwUChatCommand)
        addCommand("fly", FlyCommand)
        addCommand("testmessage", TestMessageCommand)
        addCommand("whoosh", WhooshCommand)
        addCommand("sudo", SudoCommand)
        addCommand("craft", CraftCommand)

        server.pluginManager.registerEvents(PlayerListener, this)
        server.pluginManager.registerEvents(ChatListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun addCommand(commandName: String, executor: CommandExecutor) {
        getCommand(commandName)!!.setExecutor(executor)
    }
}