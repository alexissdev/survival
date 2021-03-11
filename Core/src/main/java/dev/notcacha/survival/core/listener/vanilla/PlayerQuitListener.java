package dev.notcacha.survival.core.listener.vanilla;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.event.user.UserLeaveEvent;
import dev.notcacha.survival.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;

public class PlayerQuitListener implements Listener {

    @Inject
    private ModelCache<User> userModelCache;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        User user = userModelCache.findIfPresent(player.getUniqueId().toString()).orElseThrow(() -> new IllegalArgumentException("The user from id " + player.getUniqueId().toString() + " not exists."));

        userModelCache.removeObject(user.getId());
        Bukkit.getServer().getPluginManager().callEvent(new UserLeaveEvent(user, player));
    }
}
