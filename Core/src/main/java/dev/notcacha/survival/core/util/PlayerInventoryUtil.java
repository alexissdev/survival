package dev.notcacha.survival.core.util;

import dev.notcacha.survival.api.item.SerializableItem;
import org.bukkit.entity.Player;

import java.util.Map;

public class PlayerInventoryUtil {

    public static void clear(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void setArmorOfMap(Player player, Map<Integer, SerializableItem> serializableItemMap) {
        if (serializableItemMap.isEmpty()) {
            throw new IllegalArgumentException("The armor contents map is empty.");
        }

        player.getInventory().setHelmet(serializableItemMap.get(0).toItemStack());
        player.getInventory().setChestplate(serializableItemMap.get(1).toItemStack());
        player.getInventory().setLeggings(serializableItemMap.get(2).toItemStack());
        player.getInventory().setBoots(serializableItemMap.get(3).toItemStack());
    }
}
