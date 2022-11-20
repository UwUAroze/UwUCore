package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.sendColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object UwUChatCommand : CommandExecutor {

    var uwuChat = false

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("uwuchat")) return true

        if (toggleUwUChat()) sender.sendColoured("&#ffabdb◕.◕・ﾟ✧ &#bfffc6You've &mstarted chaos&#bfffc6 enabled UwUChat!")
        else sender.sendColoured("&#ffabdb◕︹◕ ✧ﾟ・&#ff8c8cYou've disabled UwUChat ;c")
        return true

    }

    private fun toggleUwUChat() : Boolean {
        uwuChat = !uwuChat
        return uwuChat
    }

}