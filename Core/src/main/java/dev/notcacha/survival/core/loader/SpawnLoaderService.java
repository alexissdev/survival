package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import dev.notcacha.survival.api.spawn.SpawnManager;
import dev.notcacha.survival.core.file.YamlFile;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class SpawnLoaderService implements Service {

    private final SpawnManager spawnManager;
    private final Plugin plugin;

    @Inject
    public SpawnLoaderService(SpawnManager spawnManager, Plugin plugin) {
        this.spawnManager = spawnManager;
        this.plugin = plugin;
    }

    @Override
    public void start() {
        if (!plugin.getConfig().contains("spawn")) {
            plugin.getLogger().info("[Spawn] The spawn not exists in spawn.yml!");
            return;
        }

        Map<String, Object> serializeLocation = (Map<String, Object>) plugin.getConfig().get("spawn");

        Location location = Location.deserialize(serializeLocation);

        spawnManager.setSpawnLocation(location);
    }

    @Override
    public void stop() {
        if (spawnManager.getSpawnLocation() == null) {
            return;
        }

        Map<String, Object> serializeLocation = spawnManager.getSpawnLocation().serialize();

        plugin.getConfig().set("spawn", serializeLocation);
    }
}
