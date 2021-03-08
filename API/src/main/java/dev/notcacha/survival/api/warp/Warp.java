package dev.notcacha.survival.api.warp;

import dev.notcacha.survival.api.model.SavableModel;
import org.bukkit.Location;

import java.time.LocalDateTime;

public interface Warp extends SavableModel {

    /**
     * @return The creation date from this warp.
     */

    LocalDateTime getCreationDate();

    /**
     * @return The location from this warp.
     */

    Location getLocation();

    /**
     * Set the location from this warp.
     */

    void setLocation(Location location);

}
