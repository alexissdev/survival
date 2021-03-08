package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import me.yushust.inject.AbstractModule;

public class LoaderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Service.class).named("command-loader").to(CommandLoaderService.class);
        bind(Service.class).named("event-loader").to(EventLoaderService.class);

        bind(Service.class).named("loader").to(LoaderService.class);
    }
}
