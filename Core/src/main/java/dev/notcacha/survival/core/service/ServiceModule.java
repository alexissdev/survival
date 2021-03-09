package dev.notcacha.survival.core.service;

import dev.notcacha.survival.api.service.Service;
import me.yushust.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Service.class).to(SurvivalService.class);
    }
}
