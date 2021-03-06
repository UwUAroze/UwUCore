package me.aroze.uwucore.commands;

import me.aroze.uwucore.util.ChatUtils;
import me.aroze.uwucore.UwUCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {

    private UwUCore main;

    public ConfigCommand(UwUCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("uwucore.config")) {
            sender.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eYou aren't allowed to do this! smh!"));
            return true;
        }

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 1) {

                if (args[0].equals("info")) {

                    player.sendMessage(ChatUtils.color("\n&e&lConfig:\n"));
                    for (String key : main.getConfig().getConfigurationSection("").getKeys(true)) {
                        if (!(main.getConfig().get(key) instanceof MemorySection)) {
                            if (main.getConfig().getString(key).equals("true") || main.getConfig().getString(key).equals("false")) {
                                player.sendMessage(ChatUtils.color("  &7" + key + ": " + (Boolean.parseBoolean(main.getConfig().getString(key)) ? "&atrue" : "&cfalse")));
                            } else {
                                player.sendMessage(ChatUtils.color("  &7" + key + ": &f" + main.getConfig().get(key)));
                            }
                        }
                    }

//                    player.sendMessage(ChatUtils.color("\n&e&lConfig:\n"));
//                    player.sendMessage(ChatUtils.color("  &7testWord: &f" + main.getConfig().getString("testWord")));
//                    player.sendMessage(ChatUtils.color("  &7testNumber: &f" + main.getConfig().getInt("testNumber") + ""));
//                    if (!main.getConfig().getBoolean("testBoolean")) {
//                        player.sendMessage(ChatUtils.color("  &7testBoolean: &cfalse"));
//                    } else {player.sendMessage(ChatUtils.color("  &7testBoolean: &atrue"));}
//
//                    player.sendMessage(ChatUtils.color("\n  &7testStringList:"));
//                    for (String string : main.getConfig().getStringList("testStringList")) {
//                        player.sendMessage(ChatUtils.color("    - " + string));
//                    }
                    player.sendMessage("");

                }

            }

            if (args.length == 3) {

                if (args[0].equalsIgnoreCase("set")) {

                    if (!main.getConfig().getStringList(args[1]).isEmpty()) {
                        String[] listItems = args[2].split(",");
                        main.getConfig().set(args[1] ,listItems);
                        main.saveConfig();
                        player.sendMessage(ChatUtils.color("&a✔ &7Set &a" + args[1] + " &7to\n&a • " + String.join("\n • ", listItems)));
                        return true;
                    }

                    if (main.getConfig().contains(args[1])) {

                        Object newValue;

                        try {
                            newValue = Integer.parseInt(args[2]);
                        } catch (NumberFormatException a) {
                            try {
                                newValue = Double.parseDouble(args[2]);
                            } catch (NumberFormatException b) {
                                if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
                                    newValue = Boolean.parseBoolean(args[2]);
                                } else {
                                    newValue = args[2];
                                }
                            }
                        }

                        if (!main.getConfig().get(args[1]).getClass().getName().equals(newValue.getClass().getName()) && !(main.getConfig().get(args[1]) instanceof String) ) {
                            player.sendMessage(ChatUtils.color("&c&l⚠ &7You're attempting to change the object type!"));
                            return true;
                        } else if (main.getConfig().get(args[1]) instanceof String) {
                            newValue = newValue.toString();
                        }



                        main.getConfig().set(args[1], newValue);
                        player.sendMessage(ChatUtils.color("&a✔ &7Set &a" + args[1] + " &7to &a" + args[2]));
                        main.saveConfig();
                    } else {
                        player.sendMessage(ChatUtils.color("&c&l⚠ &7There is no option named &c" + args[1]));
                    }

                }

            }

        }

        return false;
    }
}
