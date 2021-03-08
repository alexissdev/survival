package dev.notcacha.survival.api.spawn;

import org.bukkit.Location;

public interface SpawnManager {

    /**
     * @return The {@link Location} from spawn.
     */

    Location getSpawnLocation();

    /**
     * Set spawn location from server.
     */

    void setSpawnLocation(Location location);

    /**
     * @return Options from this spawn server.
     */

    SpawnOptions getOptions();

    interface SpawnOptions {

        /**
         * @return if you have to teleport to the player upon entering
         */

        boolean teleportToEnter();

        /**
         * Set the teleport to the player upon entering.
         */

        void setTeleportToEnter(boolean value);

        /**
         * @return New instance from {@link SpawnOptions}
         */

        static SpawnOptions create() {
            return new SpawnOptions() {

                private boolean teleportToEnter = false;

                @Override
                public boolean teleportToEnter() {
                    return teleportToEnter;
                }

                @Override
                public void setTeleportToEnter(boolean value) {
                    this.teleportToEnter = value;
                }
            };
        }

    }
}
