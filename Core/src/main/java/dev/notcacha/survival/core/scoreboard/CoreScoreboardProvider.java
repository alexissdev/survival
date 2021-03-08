package dev.notcacha.survival.core.scoreboard;

import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.survival.api.scoreboard.properties.provider.ScoreboardPropertiesProvider;
import org.bukkit.entity.Player;
import team.unnamed.uboard.ScoreboardManager;
import team.unnamed.uboard.builder.ScoreboardBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoreScoreboardProvider implements ScoreboardProvider {

    @Inject
    private ScoreboardManager scoreboardManager;
    @Inject
    private ScoreboardPropertiesProvider scoreboardPropertiesProvider;

    @Override
    public void setup(Player player, ScoreboardProperties.Type type) {
        scoreboardManager.getScoreboard(player.getUniqueId().toString()).ifPresent(scoreboardObjective -> {

            scoreboardManager.removeScoreboard(scoreboardObjective);
            scoreboardManager.removeScoreboard(player);

        });

        ScoreboardBuilder scoreboardBuilder = scoreboardManager.newScoreboard(player.getUniqueId().toString());
        ScoreboardProperties scoreboardProperties = scoreboardPropertiesProvider.get(player, type);

        scoreboardBuilder.setTitle(scoreboardProperties.getTitle());

        for (String line : scoreboardProperties.getLines()) {
            if (line.equals("%%empty%%")) {
                scoreboardBuilder.addSpace();

                continue;
            }
            scoreboardBuilder.addLine(line);
        }

        scoreboardBuilder.build();
    }
}
