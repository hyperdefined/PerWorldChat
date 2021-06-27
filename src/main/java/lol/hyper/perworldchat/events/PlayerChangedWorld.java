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
