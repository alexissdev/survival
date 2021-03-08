package dev.notcacha.survival.core.service;

import dev.notcacha.survival.api.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class SurvivalService implements Service {

    @Inject
    @Named("loader")
    private Service loaderService;

    @Override
    public void start() {
        loaderService.start();
    }

    @Override
    public void stop() {
        loaderService.stop();
    }
}
