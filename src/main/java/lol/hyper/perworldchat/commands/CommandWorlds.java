package lol.hyper.perworldchat.commands;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandWorlds implements CommandExecutor {

    private final PerWorldChat perWorldChat;

    public CommandWorlds(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "------------------Worlds-------------------");
        for (World world : Bukkit.getWorlds()) {
            ArrayList<String> playersInWorld = new ArrayList<>();
            for (Map.Entry<Player, World> entry : perWorldChat.playerLocations.entrySet()) {
                if (entry.getValue().equals(world)) {
                    playersInWorld.add(entry.getKey().getName());
                }
            }
            sender.sendMessage(ChatColor.GOLD + world.getName() + " (" + playersInWorld.size() + "): " + ChatColor.YELLOW + String.join(", ", playersInWorld));
        }
        sender.sendMessage(ChatColor.GOLD + "-------------------------------------------");
        return true;
    }
}
