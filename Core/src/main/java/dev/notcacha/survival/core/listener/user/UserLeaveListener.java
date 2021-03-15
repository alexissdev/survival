package dev.notcacha.survival.core.listener.user;

import dev.notcacha.survival.api.event.user.UserLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import team.unnamed.uboard.ScoreboardManager;

import javax.inject.Inject;

public class UserLeaveListener implements Listener {

    private final ScoreboardManager scoreboardManager;

    @Inject
    public UserLeaveListener(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onUserLeave(UserLeaveEvent event) {
        scoreboardManager.removeScoreboard(event.getPlayer());
    }
}
