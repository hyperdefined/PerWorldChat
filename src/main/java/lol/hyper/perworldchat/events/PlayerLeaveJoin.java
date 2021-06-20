package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveJoin implements Listener {

    private final PerWorldChat perWorldChat;

    public PlayerLeaveJoin(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        perWorldChat.playerLocations.put(event.getPlayer(), event.getPlayer().getWorld());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        perWorldChat.playerLocations.remove(event.getPlayer());
    }
}
