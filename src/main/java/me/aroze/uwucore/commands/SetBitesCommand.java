package me.aroze.uwucore.commands;

import me.aroze.uwucore.util.ChatUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Cake;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBitesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("uwucore.setbites")) {
            sender.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eYou aren't allowed to do this! smh!"));
            return true;
        }


        Block targetBlock = player.getTargetBlockExact(6);

        if (targetBlock == null) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eYou're staring at air smh."));
            return true;
        }

        if (!(targetBlock.getType().toString().contains("CAKE"))) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eYou need to be looking at a cake, dummy!"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6ePsst! /setbites <0-6>"));
            return true;
        }

        int bites;

        try { bites = Integer.parseInt(args[0]); }
        catch (NumberFormatException e) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eThat's not a number!"));
            return true;
        }

        if (bites > 6) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eThe cake can only have 6 slices taken out, fatty!"));
            return true;
        }

        if (bites < 0) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eTh-That's not how this works.."));
            return true;
        }

        Cake cakeData;
        int previousBites;

        if (targetBlock.getType().toString().contains("CANDLE")) {
            targetBlock.setType(Material.CAKE);
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eUnfortunately, we had to remove the candle ;c"));
            cakeData = (Cake) targetBlock.getBlockData();
            previousBites = 6;
        } else {
            cakeData = (Cake) targetBlock.getBlockData();
            previousBites = cakeData.getBites();
        }


        if (previousBites == bites) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eMaybe you can't count, but this cake already has " + cakeData.getBites() + " bites!"));
            return true;
        }

        cakeData.setBites(bites);

        targetBlock.setBlockData(cakeData);

        sender.sendMessage(ChatUtils.color("&#eb9bb7✔ &#ffd4e3Set cake bites! (" + previousBites + " » " + bites + ")"));

        return true;
    }
}
