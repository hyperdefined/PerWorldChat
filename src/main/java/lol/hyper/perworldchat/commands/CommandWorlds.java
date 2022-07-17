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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        perWorldChat.getAdventure().sender(sender).sendMessage(Component.text("------------------Worlds-------------------").color(NamedTextColor.GOLD));
        for (World world : Bukkit.getWorlds()) {
            Set<String> playersInWorld = new HashSet<>();
            world.getPlayers().forEach(player -> playersInWorld.add(player.getName()));
            Component worldMessage = Component.text(world.getName() + " (" + playersInWorld.size() + ": ").color(NamedTextColor.GOLD).append(Component.text(String.join(", ", playersInWorld)).color(NamedTextColor.YELLOW));
            perWorldChat.getAdventure().sender(sender).sendMessage(worldMessage);
        }
        perWorldChat.getAdventure().sender(sender).sendMessage(Component.text("-------------------------------------------").color(NamedTextColor.GOLD));
        return true;
    }
}
