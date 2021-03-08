package dev.notcacha.survival.core.flow;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

public class FlowModule extends AbstractModule {

    @Provides
    @Singleton
    public CommandManager provideCommandManager(Plugin plugin, SurvivalTranslationProvider survivalTranslationProvider) {
        CommandManager commandManager = new BukkitCommandManager(plugin.getName());

        commandManager.setTranslator(new DefaultTranslator(survivalTranslationProvider));

        return commandManager;
    }
}
