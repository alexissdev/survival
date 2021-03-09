package dev.notcacha.survival.core;

import dev.notcacha.survival.core.kit.KitModule;
import dev.notcacha.survival.core.koth.KothModule;
import dev.notcacha.survival.core.loader.LoaderModule;
import dev.notcacha.survival.core.scoreboard.ScoreboardModule;
import dev.notcacha.survival.core.service.ServiceModule;
import dev.notcacha.survival.core.tag.TagModule;
import dev.notcacha.survival.core.translation.TranslationModule;
import dev.notcacha.survival.core.user.UserModule;
import dev.notcacha.survival.core.warp.WarpModule;
import me.yushust.inject.AbstractModule;
import org.bukkit.plugin.Plugin;

public class SurvivalModule extends AbstractModule {

    private final Plugin plugin;

    public SurvivalModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);

        install(new ListeningExecutorModule());
        install(new MapperModule());
        install(new TranslationModule());
        install(new UserModule());
        install(new KitModule());
        install(new WarpModule());
        install(new KothModule());
        install(new ScoreboardModule());
        install(new TagModule());

        install(new LoaderModule());
        install(new ServiceModule());
    }
}
