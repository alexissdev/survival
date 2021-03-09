package dev.notcacha.survival.api.kit.builder;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.util.Validate;

import java.util.HashMap;
import java.util.Map;

public interface KitBuilder {

    /**
     * Set the permission of this kit.
     */

    KitBuilder setPermission(String permission);

    /**
     * Set the inventory contents of this kit.
     */

    KitBuilder setInventoryContents(Map<Integer, SerializableItem> inventoryContents);

    /**
     * Set the armor contents of this kit.
     */

    KitBuilder setArmorContents(Map<Integer, SerializableItem> armorContents);

    /**
     * @return New {@link Kit} model, using this properties from create.
     */

    Kit build();

    /**
     * @return New builder from create {@link Kit} model.
     */

    static KitBuilder newBuilder(String kitId) {
        return new KitBuilder() {

            private String permission = "survival.kit." + kitId;
            private Map<Integer, SerializableItem> inventoryContents = new HashMap<>();
            private Map<Integer, SerializableItem> armorContents = new HashMap<>();

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
                this.armorContents = Validate.nonNull(armorContents, "The armor contents from kit builder is null.");;
                return this;
            }

            @Override
            public Kit build() {
                return new Kit() {
                    @Override
                    public String getId() {
                        return kitId;
                    }

                    @Override
                    public Configuration getConfiguration() {
                        return new Configuration() {

                            private String kitPermission = permission;

                            @Override
                            public String getPermission() {
                                return kitPermission;
                            }

                            @Override
                            public void setPermission(String permission) {
                                this.kitPermission = permission;
                            }
                        };
                    }

                    @Override
                    public Contents getContents() {
                        return new Contents() {
                            @Override
                            public Map<Integer, SerializableItem> getInventoryContents() {
                                return inventoryContents;
                            }

                            @Override
                            public Map<Integer, SerializableItem> getArmorContents() {
                                return armorContents;
                            }
                        };
                    }
                };
            }
        };
    }
}
