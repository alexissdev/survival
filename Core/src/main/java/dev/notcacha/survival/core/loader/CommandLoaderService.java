package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import dev.notcacha.survival.core.command.*;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.builder.AnnotatedCommandBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.yushust.inject.Injector;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommandLoaderService implements Service {

    private final Plugin plugin;
    private final CommandManager commandManager;
    private final Injector injector;
    private final CommandProvider commandProvider;

    @Inject
    public CommandLoaderService(Plugin plugin, CommandManager commandManager, Injector injector, CommandProvider commandProvider) {
        this.plugin = plugin;
        this.commandManager = commandManager;
        this.injector = injector;
        this.commandProvider = commandProvider;
    }

    @Override
    public void start() {
        plugin.getLogger().info("[Command-Loader] The command loader service has been start.");

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(
                new AnnotatedCommandBuilderImpl(partInjector),
                (clazz, parent) -> injector.getInstance(clazz)
        );

        register(
                treeBuilder,
                commandProvider.get()
        );
    }

    private void register(AnnotatedCommandTreeBuilder builder, CommandClass... commandClasses) {
        for (CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(builder.fromClass(commandClass));
        }
    }

    @Override
    public void stop() {
        plugin.getLogger().info("[Command-Loader] The command loader service has been stopped.");

        commandManager.unregisterAll();
    }

    @Singleton
    private static class CommandProvider {

        private final StatisticCommand statisticCommand;
        private final WarpCommand warpCommand;
        private final SpawnCommand spawnCommand;
        private final SetSpawnCommand setSpawnCommand;
        private final KitCommand kitCommand;

        @Inject
        private CommandProvider(StatisticCommand statisticCommand, WarpCommand warpCommand, SpawnCommand spawnCommand, SetSpawnCommand setSpawnCommand, KitCommand kitCommand) {
            this.statisticCommand = statisticCommand;
            this.warpCommand = warpCommand;
            this.spawnCommand = spawnCommand;
            this.setSpawnCommand = setSpawnCommand;
            this.kitCommand = kitCommand;
        }

        public CommandClass[] get() {
            return new CommandClass[]{
                    statisticCommand,
                    warpCommand,
                    spawnCommand,
                    setSpawnCommand,
                    kitCommand
            };
        }

    }
}
