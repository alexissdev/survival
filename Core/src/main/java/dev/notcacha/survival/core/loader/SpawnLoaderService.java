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

    @Inject
    private SpawnManager spawnManager;
    @Inject
    private Plugin plugin;

    @Override
    public void start() {
        YamlFile file = new YamlFile(plugin, "spawn");

        if (!file.contains("spawn")) {
            plugin.getLogger().info("[Spawn] The spawn not exists in spawn.yml!");
            return;
        }

        Map<String, Object> serializeLocation = (Map<String, Object>) file.get("spawn");

        Location location = Location.deserialize(serializeLocation);

        spawnManager.setSpawnLocation(location);
    }

    @Override
    public void stop() {
        YamlFile file = new YamlFile(plugin, "spawn");

        if (spawnManager.getSpawnLocation() == null) {
            return;
        }

        Map<String, Object> serializeLocation = spawnManager.getSpawnLocation().serialize();

        file.set("spawn", serializeLocation);
    }
}
