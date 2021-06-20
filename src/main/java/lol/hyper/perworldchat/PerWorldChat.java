package lol.hyper.perworldchat;

import lol.hyper.perworldchat.commands.CommandWorlds;
import lol.hyper.perworldchat.events.AsyncPlayerChat;
import lol.hyper.perworldchat.events.PlayerChangedWorld;
import lol.hyper.perworldchat.events.PlayerLeaveJoin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PerWorldChat extends JavaPlugin {

    public final HashMap<Player, World> playerLocations = new HashMap<>();

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
    }
}
