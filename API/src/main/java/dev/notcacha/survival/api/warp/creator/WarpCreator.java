package dev.notcacha.survival.api.warp.creator;

import dev.notcacha.survival.api.warp.Warp;
import org.bukkit.Location;

import java.time.LocalDateTime;

public interface WarpCreator {

    /**
     * Create {@link Warp} instance
     *
     * @param id from this warp has been created.
     */

    static Warp create(String id) {
        return new Warp() {

            private final LocalDateTime time = LocalDateTime.now();
            private Location location = null;

            @Override
            public String getId() {
                return id;
            }

            @Override
            public LocalDateTime getCreationDate() {
                return time;
            }

            @Override
            public Location getLocation() {
                return location;
            }

            @Override
            public void setLocation(Location location) {
                this.location = location;
            }
        };
    }

}
