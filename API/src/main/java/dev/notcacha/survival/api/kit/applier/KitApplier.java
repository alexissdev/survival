package dev.notcacha.survival.api.kit.applier;

import dev.notcacha.survival.api.kit.Kit;
import org.bukkit.entity.Player;

public interface KitApplier {

    /**
     * Apply the kit from player.
     * @param player to which the kit will be applied.
     * @param kit the one that will be applied.
     */

    void apply(Player player, Kit kit);
}
