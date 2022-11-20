package me.aroze.uwucore.command

import me.aroze.uwucore.util.isRightless
import me.aroze.uwucore.util.isStupid
import me.aroze.uwucore.util.sendFinalColoured
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object TestMessageCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.isRightless("testmessage")) return true
        if (args.isEmpty()) return sender.isStupid("Specify a message to test, colour/hex codes can be used :)")

        return sender.sendFinalColoured(args.joinToString(" "))

    }

}