package me.aroze.uwucore.command

import me.aroze.arozeutils.minecraft.command.CommandInfo
import me.aroze.arozeutils.minecraft.command.FancyCommand
import org.bukkit.command.CommandSender

@CommandInfo(
    description = "Opens a virtual crafting table",
    permission = "craft",
    aliases = ["workbench"],
    permissionMessage = "You aren't allowed to use /craft. Place one down, brokie.",
)
object CraftCommand : FancyCommand("craft") {

    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {

        val target = handleTarget(sender, args, "We can't open the crafting table of an offline player ;c") ?: return
        target.openWorkbench(null, true)

        if (sender != target) {
            sender.sendPrimary("You've opened a crafting table for &s${target.name}&p, awhh")
            target.sendPrimary("Some cutie opened a crafting table for you ;3")
        } else target.sendPrimary("Lazy ass")

    }

}