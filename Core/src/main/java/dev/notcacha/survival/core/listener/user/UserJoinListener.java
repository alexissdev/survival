package dev.notcacha.survival.core.listener.user;

import dev.notcacha.survival.api.event.user.UserJoinEvent;
import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class UserJoinListener implements Listener {

    @Inject
    private ScoreboardProvider scoreboardProvider;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onUserJoin(UserJoinEvent event) {
        scoreboardProvider.setup(event.getPlayer(), ScoreboardProperties.Type.DEFAULT);
    }
}
