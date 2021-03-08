package dev.notcacha.survival.core.spawn;

import dev.notcacha.survival.api.spawn.SpawnManager;
import org.bukkit.Location;

import javax.inject.Singleton;

@Singleton
public class CoreSpawnManager implements SpawnManager {

    private Location location = null;
    private final SpawnOptions options = SpawnOptions.create();

    @Override
    public Location getSpawnLocation() {
        return location;
    }

    @Override
    public void setSpawnLocation(Location location) {
        this.location = location;
    }

    @Override
    public SpawnOptions getOptions() {
        return options;
    }
}
