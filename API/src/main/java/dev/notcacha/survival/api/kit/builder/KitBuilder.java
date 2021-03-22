package dev.notcacha.survival.api.kit.builder;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.kit.Kit;

import java.util.Map;

public interface KitBuilder {

    /**
     * Set the permission from kit.
     */

    KitBuilder setPermission(String permission);

    /**
     * Set the inventory contents from kit in map format.
     */

    KitBuilder setInventoryContents(Map<Integer, SerializableItem> inventoryContents);

    /**
     * Set the armor contents from kit in map format.
     */

    KitBuilder setArmorContents(Map<Integer, SerializableItem> armorContents);

    /**
     * @return new {@link Kit} using this properties.
     */

    Kit build();
}
