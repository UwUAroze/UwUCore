package me.aroze.uwucore.listener

import me.aroze.uwucore.command.UwUChatCommand.uwuChat
import me.aroze.uwucore.util.uwuify
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

object ChatListener : Listener {

    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        var message = e.message
        if (uwuChat) message = uwuify(message)
        e.message = message
    }

}