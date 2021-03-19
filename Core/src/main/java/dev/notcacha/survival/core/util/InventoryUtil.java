package dev.notcacha.survival.core.util;

import dev.notcacha.survival.api.item.SerializableItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class InventoryUtil {

    public static Inventory ofMap(String title, Map<Integer, SerializableItem> map) {

        Inventory inventory = Bukkit.getServer().createInventory(null, 54, title);

        map.forEach((position, item) -> {
            inventory.setItem(position, item.toItemStack());
        });

        return inventory;
    }
}
