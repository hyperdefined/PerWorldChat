package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class Events implements Listener {

    private final PerWorldChat perWorldChat;

    public Events(PerWorldChat perWorldChat) {
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        World currentWorld = event.getPlayer().getWorld();
        for (Map.Entry<Player, World> entry : perWorldChat.playerLocations.entrySet()) {
            if (!entry.getValue().equals(currentWorld)) {
                event.getRecipients().remove(entry.getKey());
            }
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        perWorldChat.playerLocations.replace(event.getPlayer(), event.getPlayer().getWorld());
    }
}
