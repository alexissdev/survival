package dev.notcacha.survival.api.scoreboard.properties.provider;

import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import org.bukkit.entity.Player;

public interface ScoreboardPropertiesProvider {

    /**
     * @param player from get Scoreboard Properties.
     * @param type   of Scoreboard Properties.
     * @return the player's Scoreboard Properties.
     */

    ScoreboardProperties get(Player player, ScoreboardProperties.Type type);
}
