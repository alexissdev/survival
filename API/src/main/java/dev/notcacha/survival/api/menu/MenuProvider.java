package dev.notcacha.survival.api.menu;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface MenuProvider {

    /**
     * @return possible objects that could be needed when the inventory is created.
     */

    @Nullable
    Map<String, Object> getObjects();

    /**
     * @return The final inventory.
     */

    Inventory get();
}
