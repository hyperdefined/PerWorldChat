package lol.hyper.perworldchat;

import lol.hyper.perworldchat.commands.CommandWorlds;
import lol.hyper.perworldchat.events.AsyncPlayerChat;
import lol.hyper.perworldchat.events.PlayerChangedWorld;
import lol.hyper.perworldchat.events.PlayerLeaveJoin;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public final class PerWorldChat extends JavaPlugin {

    // we track which world each player is in
    public final HashMap<World, Set<Player>> playerLocations = new HashMap<>();

    public AsyncPlayerChat asyncPlayerChat;
    public PlayerChangedWorld playerChangedWorld;
    public PlayerLeaveJoin playerLeaveJoin;

    @Override
    public void onEnable() {
        asyncPlayerChat = new AsyncPlayerChat(this);
        playerChangedWorld = new PlayerChangedWorld(this);
        playerLeaveJoin = new PlayerLeaveJoin(this);

        Bukkit.getServer().getPluginManager().registerEvents(asyncPlayerChat, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerChangedWorld, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerLeaveJoin, this);

        this.getCommand("worlds").setExecutor(new CommandWorlds(this));

        Metrics metrics = new Metrics(this, 11754);

        // initialize all the worlds
        // if we don't throw an empty set into the hashmap for each world, they are null
        // I don't feel like null checking so this is easier :)
        for (World world : Bukkit.getWorlds()) {
            Set<Player> empty = Collections.emptySet();
            playerLocations.put(world, empty);
        }
    }
}
