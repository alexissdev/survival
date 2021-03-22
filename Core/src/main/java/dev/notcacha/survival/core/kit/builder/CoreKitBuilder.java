package dev.notcacha.survival.core.kit.builder;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.kit.builder.KitBuilder;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.kit.CoreKit;
import dev.notcacha.survival.core.kit.CoreKitConfiguration;

import java.util.HashMap;
import java.util.Map;

public class CoreKitBuilder implements KitBuilder {

    private final String id;
    private String permission;
    private Map<Integer, SerializableItem> inventoryContents;
    private Map<Integer, SerializableItem> armorContents;

    public CoreKitBuilder(String id) {
        this.id = Validate.nonNull(id, "The id from kit is null.");
        this.permission = "survival.kit." + id;
        this.inventoryContents = new HashMap<>();
        this.armorContents = new HashMap<>();
    }

    @Override
    public KitBuilder setPermission(String permission) {
        this.permission = Validate.nonNull(permission, "The permission from kit builder is null.");

        return this;
    }

    @Override
    public KitBuilder setInventoryContents(Map<Integer, SerializableItem> inventoryContents) {
        this.inventoryContents = Validate.nonNull(inventoryContents, "The inventory contents from kit builder is null.");

        return this;
    }

    @Override
    public KitBuilder setArmorContents(Map<Integer, SerializableItem> armorContents) {
        this.armorContents = Validate.nonNull(armorContents, "The armor contents from kit builder is null.");

        return this;
    }

    @Override
    public Kit build() {
        return new CoreKit(
                id,
                new CoreKitConfiguration(
                        permission
                ),
                new Kit.Contents() {
                    @Override
                    public Map<Integer, SerializableItem> getInventoryContents() {
                        return inventoryContents;
                    }

                    @Override
                    public Map<Integer, SerializableItem> getArmorContents() {
                        return armorContents;
                    }
                }
        );
    }

    public static KitBuilder newBuilder(String kitId) {
        return new CoreKitBuilder(kitId);
    }
    
}
