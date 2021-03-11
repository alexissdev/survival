package dev.notcacha.survival.api.tag;

import dev.notcacha.survival.api.model.SavableModel;

public interface Tag extends SavableModel {

    /**
     * @return The prefix of this tag.
     */

    String getPrefix();

    /**
     * Set the prefix from tag.
     */

    void setPrefix(String prefix);

    /**
     * @return The suffix of this tag.
     */

    String getSuffix();

    /**
     * Set the suffix from tag.
     */

    void setSuffix(String suffix);

    /**
     * @return the color code that will be set in the player's name
     */

    char getColorCodeFromPlayerName();

    /**
     * Set the color code from player name of tag.
     */

    void setColorCodeFromPlayerName(char colorCode);

}
