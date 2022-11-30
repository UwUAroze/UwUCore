package me.aroze.uwucore

import me.aroze.uwucore.command.*
import me.aroze.uwucore.listener.ChatListener
import me.aroze.uwucore.listener.PlayerListener
import me.aroze.uwucore.util.setCommand
import org.bukkit.plugin.java.JavaPlugin

class UwUCore : JavaPlugin() {

    companion object {
        fun getInstance() : UwUCore { return getPlugin(UwUCore::class.java) }
    }

    override fun onEnable() {

        saveDefaultConfig()

        GamemodeCommand.setCommand("gamemode")
        HealCommand.setCommand("heal")
        KittyCannonCommand.setCommand("kittycannon")
        FeedCommand.setCommand("feed")
        SmiteCommand.setCommand("smite")
        PingCommand.setCommand("ping")
        FlySpeedCommand.setCommand("flyspeed")
        WalkSpeedCommand.setCommand("walkspeed")
        UwUChatCommand.setCommand("uwuchat")
        FlyCommand.setCommand("fly")
        TestMessageCommand.setCommand("testmessage")
        WhooshCommand.setCommand("whoosh")
        SudoCommand.setCommand("sudo")
        CraftCommand.setCommand("craft")
        SkullCommand.setCommand("skull")
        RepairCommand.setCommand("repair")
        ShrugCommand.setCommand("shrug")
        TableflipCommand.setCommand("tableflip")

        server.pluginManager.registerEvents(PlayerListener, this)
        server.pluginManager.registerEvents(ChatListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}