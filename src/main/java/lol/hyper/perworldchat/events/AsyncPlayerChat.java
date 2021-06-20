package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;

public class AsyncPlayerChat implements Listener {

    private final PerWorldChat perWorldChat;

    public AsyncPlayerChat(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
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
}
