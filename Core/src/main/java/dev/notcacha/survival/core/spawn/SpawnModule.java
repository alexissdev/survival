package dev.notcacha.survival.core.spawn;

import dev.notcacha.survival.api.spawn.SpawnManager;
import me.yushust.inject.AbstractModule;

public class SpawnModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SpawnManager.class).to(CoreSpawnManager.class);
    }
}
