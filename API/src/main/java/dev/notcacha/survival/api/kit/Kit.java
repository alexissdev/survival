package dev.notcacha.survival.api.kit;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.model.SavableModel;

import java.util.Map;

public interface Kit extends SavableModel {

    /**
     * @return The configuration of this kit.
     */

    Configuration getConfiguration();

    interface Configuration {

        /**
         * @return The permission of this kit.
         */

        String getPermission();

        /**
         * Set the permission of this kit.
         */

        void setPermission(String permission);

    }

    /**
     * @return The contents of this kit.
     */

    Contents getContents();

    interface Contents {

        /**
         * @return The inventory contents of this kit.
         */

        Map<Integer, SerializableItem> getInventoryContents();

        /**
         * @return The armor contents of this kit.
         */

        Map<Integer, SerializableItem> getArmorContents();

    }
}
