package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import jdk.jfr.Name;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class LoaderService implements Service {

    @Inject
    private Plugin plugin;

    @Inject
    @Named("event-loader")
    private Service eventLoaderService;
    @Inject
    @Name("command-loader")
    private Service commandLoaderService;
    @Inject
    @Name("spawn-loader")
    private Service spawnLoaderService;

    @Override
    public void start() {
        plugin.getLogger().info("[Loader] The loader service has been started.");

        spawnLoaderService.start();

        eventLoaderService.start();
        commandLoaderService.stop();
    }

    @Override
    public void stop() {
        plugin.getLogger().info("[Loader] The loader service has been stopped.");

        spawnLoaderService.stop();
        eventLoaderService.stop();
        commandLoaderService.stop();
    }
}
