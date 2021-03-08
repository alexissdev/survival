package dev.notcacha.survival.api.scoreboard.properties;

import java.util.List;

public interface ScoreboardProperties {

    /**
     * @return The title of scoreboard.
     */

    String getTitle();

    /**
     * @return The lines of scoreboard.
     */

    List<String> getLines();

    /**
     * The types of scoreboard properties.
     */

    enum Type {

        DEFAULT,
        KOTH

    }
}
