package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class LoaderService implements Service {

    private final Plugin plugin;
    private final Service eventLoaderService;
    private final Service commandLoaderService;
    private final Service spawnLoaderService;

    @Inject
    public LoaderService(Plugin plugin, @Named("event-loader") Service eventLoaderService,
                         @Named("command-loader") Service commandLoaderService, @Named("spawn-loader") Service spawnLoaderService) {
        this.plugin = plugin;
        this.eventLoaderService = eventLoaderService;
        this.commandLoaderService = commandLoaderService;
        this.spawnLoaderService = spawnLoaderService;
    }

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
