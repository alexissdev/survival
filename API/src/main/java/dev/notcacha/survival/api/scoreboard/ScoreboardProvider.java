package dev.notcacha.survival.api.scoreboard;

import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import org.bukkit.entity.Player;

public interface ScoreboardProvider {

    /**
     * Setup the scoreboard from player.
     *
     * @param player has been set scoreboard.
     * @param type of scoreboard has been set.
     */

    void setup(Player player, ScoreboardProperties.Type type);
}
