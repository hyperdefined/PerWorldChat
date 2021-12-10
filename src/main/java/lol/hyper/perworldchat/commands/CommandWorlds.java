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

package lol.hyper.perworldchat.commands;

import lol.hyper.perworldchat.PerWorldChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CommandWorlds implements CommandExecutor {

    private final PerWorldChat perWorldChat;

    public CommandWorlds(PerWorldChat perWorldChat) {
        this.perWorldChat = perWorldChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "------------------Worlds-------------------");
        for (World world : Bukkit.getWorlds()) {
            if (perWorldChat.playerLocations.get(world) == null) {
                continue; // ignore null worlds
            }
            // create a new set that has all the players per world
            // probably better way to do this, but I can't be bothered
            Set<String> playersInWorld = new HashSet<>();
            perWorldChat.playerLocations.get(world).forEach(player -> playersInWorld.add(player.getName()));
            sender.sendMessage(ChatColor.GOLD + world.getName() + " (" + perWorldChat.playerLocations.get(world).size() + "): " + ChatColor.YELLOW + String.join(", ", playersInWorld));
        }
        sender.sendMessage(ChatColor.GOLD + "-------------------------------------------");
        return true;
    }
}
