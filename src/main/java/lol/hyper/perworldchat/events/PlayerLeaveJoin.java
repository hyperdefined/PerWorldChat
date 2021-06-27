/*
 * This file is part of PerWorldChat.
 *
 * PerWorldChat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PerWorldChat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PerWorldChat.  If not, see <https://www.gnu.org/licenses/>.
 */

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

        // add player to the world list when they login
        perWorldChat.playerLocations.get(world).add(player);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        // remove player from world list when they leave the server
        perWorldChat.playerLocations.get(world).remove(player);
    }
}
