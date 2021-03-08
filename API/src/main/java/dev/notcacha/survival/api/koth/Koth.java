package dev.notcacha.survival.api.koth;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.util.Cuboid;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Koth extends SavableModel {

    /**
     * @return The creation date from this koth.
     */

    LocalDateTime getCreationDate();

    /**
     * @return The repository of cuboid from this koth.
     */

    Optional<Cuboid.Repository> getCuboidRepository();

    /**
     * Set the repository of cuboid from koth.
     */

    void setCuboidRepository(Cuboid.Repository cuboidRepository);

    /**
     * @return The settings from this koth.
     */

    Settings getSettings();

    interface Settings {

        /**
         * @return The koth is started.
         */

        boolean isStarted();

        /**
         * Set state from koth.
         */

        void setStarted(boolean value);

        /**
         * @return The id of the player who is capturing the koth
         */

        Optional<String> getCapturingId();

        /**
         * Set id from player capturing the koth.
         */

        void setCapturingId(String capturingId);

        /**
         * @return The id of all the players that are inside the koth.
         */

        Set<String> getPlayersIdWhoAreInside();

    }

    /**
     * @return The loot of this koth.
     */

    Loot getLoot();


    interface Loot {

        /**
         * @return The items and positions from loot of koth.
         */

        Map<Integer, SerializableItem> getItems();

    }
}
