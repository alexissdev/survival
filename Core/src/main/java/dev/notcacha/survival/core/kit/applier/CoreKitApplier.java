package dev.notcacha.survival.core.kit.applier;

import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.kit.applier.KitApplier;
import dev.notcacha.survival.core.util.PlayerInventoryUtil;
import org.bukkit.entity.Player;

import javax.inject.Singleton;

import static dev.notcacha.survival.core.util.PlayerInventoryUtil.*;

@Singleton
public class CoreKitApplier implements KitApplier {

    @Override
    public void apply(Player player, Kit kit) {
        clear(player);

        kit.getContents().getInventoryContents().forEach((key, value) -> player.getInventory().setItem(key, value.toItemStack()));

        setArmorOfMap(player, kit.getContents().getArmorContents());
    }
}
