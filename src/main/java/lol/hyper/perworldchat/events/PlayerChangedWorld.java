package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorld implements Listener {

    private final PerWorldChat perWorldChat;

    public PlayerChangedWorld(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        perWorldChat.playerLocations.replace(event.getPlayer(), event.getPlayer().getWorld());
    }
}
