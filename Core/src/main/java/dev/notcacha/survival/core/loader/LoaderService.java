package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
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

    @Override
    public void start() {
        plugin.getLogger().info("[Loader] The loader service has been started.");

        eventLoaderService.start();
    }

    @Override
    public void stop() {
        plugin.getLogger().info("[Loader] The loader service has been stopped.");

        eventLoaderService.stop();
    }
}
