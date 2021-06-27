package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveJoin implements Listener {

    private final PerWorldChat perWorldChat;

    public PlayerLeaveJoin(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        perWorldChat.playerLocations.get(world).add(player);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        perWorldChat.playerLocations.get(world).remove(player);
    }
}
