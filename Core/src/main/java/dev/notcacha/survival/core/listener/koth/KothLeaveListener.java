package dev.notcacha.survival.core.listener.koth;

import dev.notcacha.survival.api.event.koth.KothLeaveEvent;
import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class KothLeaveListener implements Listener {

    private final ScoreboardProvider scoreboardProvider;

    @Inject
    public KothLeaveListener(ScoreboardProvider scoreboardProvider) {
        this.scoreboardProvider = scoreboardProvider;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onKothLeave(KothLeaveEvent event) {
        scoreboardProvider.setup(event.getPlayer(), ScoreboardProperties.Type.DEFAULT);
    }
}
