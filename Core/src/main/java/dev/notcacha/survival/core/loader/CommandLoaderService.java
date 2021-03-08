package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import dev.notcacha.survival.core.command.SetSpawnCommand;
import dev.notcacha.survival.core.command.SpawnCommand;
import dev.notcacha.survival.core.command.StatisticCommand;
import dev.notcacha.survival.core.command.WarpCommand;
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

    @Inject
    private Plugin plugin;
    @Inject
    private CommandManager commandManager;
    @Inject
    private Injector injector;

    @Inject
    private CommandProvider commandProvider;

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

        @Inject
        private StatisticCommand statisticCommand;
        @Inject
        private WarpCommand warpCommand;
        @Inject
        private SpawnCommand spawnCommand;
        @Inject
        private SetSpawnCommand setSpawnCommand;

        public CommandClass[] get() {
            return new CommandClass[]{
                    statisticCommand,
                    warpCommand,
                    spawnCommand,
                    setSpawnCommand
            };
        }

    }
}
