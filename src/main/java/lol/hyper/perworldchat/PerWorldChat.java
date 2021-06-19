package lol.hyper.perworldchat;

import lol.hyper.perworldchat.commands.CommandWorlds;
import lol.hyper.perworldchat.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PerWorldChat extends JavaPlugin {

    public final HashMap<Player, World> playerLocations = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
        this.getCommand("worlds").setExecutor(new CommandWorlds(this));
    }
}
