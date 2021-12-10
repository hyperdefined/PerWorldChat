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

package lol.hyper.perworldchat;

import lol.hyper.perworldchat.commands.CommandWorlds;
import lol.hyper.perworldchat.events.AsyncPlayerChat;
import lol.hyper.perworldchat.events.PlayerChangedWorld;
import lol.hyper.perworldchat.events.PlayerLeaveJoin;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class PerWorldChat extends JavaPlugin {

    // we track which world each player is in
    public final HashMap<World, Set<Player>> playerLocations = new HashMap<>();
    public final Logger logger = this.getLogger();

    public AsyncPlayerChat asyncPlayerChat;
    public PlayerChangedWorld playerChangedWorld;
    public PlayerLeaveJoin playerLeaveJoin;

    @Override
    public void onEnable() {
        asyncPlayerChat = new AsyncPlayerChat(this);
        playerChangedWorld = new PlayerChangedWorld(this);
        playerLeaveJoin = new PlayerLeaveJoin(this);

        Bukkit.getServer().getPluginManager().registerEvents(asyncPlayerChat, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerChangedWorld, this);
        Bukkit.getServer().getPluginManager().registerEvents(playerLeaveJoin, this);

        this.getCommand("worlds").setExecutor(new CommandWorlds(this));

        new Metrics(this, 11754);
    }
}
