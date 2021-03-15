package dev.notcacha.survival.core.listener.koth;

import dev.notcacha.survival.api.event.koth.KothEnterEvent;
import dev.notcacha.survival.api.koth.Koth;
import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class KothEnterListener implements Listener {

    private final MessageHandler messageHandler;
    private final ScoreboardProvider scoreboardProvider;

    @Inject
    public KothEnterListener(MessageHandler messageHandler, ScoreboardProvider scoreboardProvider) {
        this.messageHandler = messageHandler;
        this.scoreboardProvider = scoreboardProvider;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onKothEnterEvent(KothEnterEvent event) {
        Koth koth = event.getKoth();
        Player player = event.getPlayer();

        if (!koth.getSettings().isStarted()) {
            return;
        }

        //TODO: Set the koth scoreboard of player.
        scoreboardProvider.setup(player, ScoreboardProperties.Type.KOTH);

        if (koth.getSettings().getCapturingId().isPresent()) {
            return;
        }

        //TODO: Set player id from capturing id of koth.
        koth.getSettings().setCapturingId(player.getUniqueId().toString());
    }
}
