package dev.notcacha.survival.core.scoreboard;

import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.survival.api.scoreboard.properties.provider.ScoreboardPropertiesProvider;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CoreScoreboardPropertiesProvider implements ScoreboardPropertiesProvider {

    @Inject
    private MessageHandler messageHandler;

    private final static String SCOREBOARD_PATH = "scoreboard.%s.%s";

    @Override
    public ScoreboardProperties get(Player player, ScoreboardProperties.Type type) {
        switch (type) {

            case KOTH: {
                return getOfYaml(player, String.format(SCOREBOARD_PATH, "koth", "%s"));
            }

            case DEFAULT: {
                return getOfYaml(player, String.format(SCOREBOARD_PATH, "default", "%s"));
            }

            default: {
                throw new IllegalArgumentException("Invalid type of scoreboard properties.");
            }

        }
    }

    private ScoreboardProperties getOfYaml(Player player, String path) {
        return new ScoreboardProperties() {
            @Override
            public String getTitle() {
                return messageHandler.get(player, String.format(path, "title"));
            }

            @Override
            public List<String> getLines() {
                return messageHandler.getMany(player, String.format(path, "lines"));
            }
        };
    }
}
