package lol.hyper.perworldchat.commands;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class CommandWorlds implements CommandExecutor {

    private final PerWorldChat perWorldChat;

    public CommandWorlds(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "------------------Worlds-------------------");
        for (World world : Bukkit.getWorlds()) {
            // create a new set that has all of the players per world
            // probably better way to do this but I can't be bothered
            Set<String> playersInWorld = new HashSet<>();
            perWorldChat.playerLocations.get(world).forEach(player -> playersInWorld.add(player.getName()));
            sender.sendMessage(ChatColor.GOLD + world.getName() + " (" + perWorldChat.playerLocations.get(world).size() + "): " + ChatColor.YELLOW + String.join(", ", playersInWorld));
        }
        sender.sendMessage(ChatColor.GOLD + "-------------------------------------------");
        return true;
    }
}
