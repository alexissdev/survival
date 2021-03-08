package dev.notcacha.survival.api.tag.applier;

import org.bukkit.entity.Player;

public interface TagApplier {

    /**
     * @see TagApplier#apply(Player, String)
     */

    default void apply(Player player) {
        apply(
                player,
                player.getName()
        );
    }

    /**
     * Apply tag from {@param player}
     *
     * @param tagId from get tag.
     */

    void apply(Player player, String tagId);
}
