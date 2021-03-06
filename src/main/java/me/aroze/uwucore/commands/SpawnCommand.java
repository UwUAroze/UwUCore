package me.aroze.uwucore.commands;

import me.aroze.uwucore.UwUCore;
import me.aroze.uwucore.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    public static ArrayList<Player> slashSpawningPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        Location spawnLoc = Bukkit.getWorld("Lobby").getSpawnLocation(); // hardcoded for now, /setspawn soon:tm:
        int spawnTimer = UwUCore.getInstance().getConfig().getInt("spawn.spawnTimer");

        // Instant spawning (if config option for spawn timer is set to 0 (or less))
        if (spawnTimer <= 0) {
            player.teleport(spawnLoc);
            player.sendMessage(ChatUtils.color("&#eb9bb7✔ &#ffd4e3Thy has brung thee to spawn!"));
            return true;
        }

        if (slashSpawningPlayers.contains(player)) {
            player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eYou must hate that place, but you can't leave any faster!"));
            return true;
        }

        slashSpawningPlayers.add(player);

        if (spawnTimer == 1) {
            player.sendMessage(ChatUtils.color("&#c49baa&oPsst! Try not to move for the next second or so!"));
        } else {
            player.sendMessage(ChatUtils.color("&#c49baa&oPsst! Try not to move for the next " + spawnTimer + " or so seconds!"));
        }

        BukkitTask spawnTask = new BukkitRunnable() {

            Location startLoc = player.getLocation();
            int ticks = 1;

            @Override
            public void run() {
                ticks++;
                if (ticks >= spawnTimer*20)  {
                    player.teleport(spawnLoc);
                    player.sendMessage(ChatUtils.color("&#eb9bb7✔ &#ffd4e3Thy has brung thee to spawn!"));
                    slashSpawningPlayers.remove(player);
                    this.cancel();
                    return;
                }
                if (player.getLocation().distance(startLoc) > 0.5) {
                    player.sendMessage(ChatUtils.color("&#ff6e6e⚠ &#ff7f6eTeleportation cancelled. You had one job smh."));
                    this.cancel();
                    slashSpawningPlayers.remove(player);
                    return;
                }
            }

        }.runTaskTimer(UwUCore.getInstance(), 0, 1);

        return true;
    }

}
