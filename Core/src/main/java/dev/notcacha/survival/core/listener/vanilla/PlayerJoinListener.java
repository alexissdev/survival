package dev.notcacha.survival.core.listener.vanilla;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.event.user.UserJoinEvent;
import dev.notcacha.survival.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;

public class PlayerJoinListener implements Listener {

    @Inject
    private ObjectCache<User> userObjectCache;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        userObjectCache.findIfPresent(event.getPlayer().getUniqueId().toString()).ifPresent(user -> Bukkit.getServer().getPluginManager().callEvent(new UserJoinEvent(user, event.getPlayer())));
    }
}
