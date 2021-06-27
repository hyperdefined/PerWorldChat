package lol.hyper.perworldchat.events;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorld implements Listener {

    private final PerWorldChat perWorldChat;

    public PlayerChangedWorld(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World newWorld = player.getWorld();
        World oldWorld = event.getFrom();

        perWorldChat.playerLocations.get(oldWorld).remove(player); // remove them from the oldWorld list
        perWorldChat.playerLocations.get(newWorld).add(player); // put them into the newWorld list
    }
}
