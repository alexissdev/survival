package dev.notcacha.survival.core;

import dev.notcacha.survival.api.service.Service;
import me.yushust.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public class Survival extends JavaPlugin {

    @Inject
    private Service service;

    @Override
    public void onEnable() {
        Injector injector = Injector.create(new SurvivalModule(this));
        injector.injectMembers(this);

        service.start();
    }

    @Override
    public void onDisable() {
        service.stop();
    }
}
