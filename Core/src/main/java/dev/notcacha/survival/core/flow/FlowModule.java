package dev.notcacha.survival.core.flow;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import me.fixeddev.commandflow.usage.DefaultUsageBuilder;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;
import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

public class FlowModule extends AbstractModule {

    @Provides
    @Singleton
    public CommandManager provideCommandManager(Plugin plugin, SurvivalTranslationProvider survivalTranslationProvider) {
        CommandManager commandManager = new BukkitCommandManager(plugin.getName());
        commandManager.setUsageBuilder(new DefaultUsageBuilder() {
            @Override
            public Component getUsage(CommandContext commandContext) {
                return TextComponent.of("Usage: ")
                        .color(TextColor.RED)
                        .append(super.getUsage(commandContext));
            }
        });

        commandManager.setTranslator(new DefaultTranslator(survivalTranslationProvider));

        return commandManager;
    }
}
