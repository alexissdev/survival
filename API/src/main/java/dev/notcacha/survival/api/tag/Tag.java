package dev.notcacha.survival.api.tag;

import dev.notcacha.survival.api.model.SavableModel;

public interface Tag extends SavableModel {

    /**
     * @return The prefix of this tag.
     */

    String getPrefix();

    /**
     * @return THe suffix of this tag.
     */

    String getSuffix();

    /**
     * @return the color code that will be set in the player's name
     */

    char getColorCodeFromPlayerName();

}
