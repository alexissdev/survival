package dev.notcacha.survival.core.scoreboard;

import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.provider.ScoreboardPropertiesProvider;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;
import org.bukkit.plugin.Plugin;
import team.unnamed.uboard.ScoreboardManager;
import team.unnamed.uboard.SimpleScoreboardManager;

import javax.inject.Singleton;

public class ScoreboardModule extends AbstractModule {

    @Provides
    @Singleton
    public ScoreboardManager provideScoreboardManager(Plugin plugin) {
        return new SimpleScoreboardManager(plugin);
    }

    @Override
    protected void configure() {
        bind(ScoreboardPropertiesProvider.class).to(CoreScoreboardPropertiesProvider.class);
        bind(ScoreboardProvider.class).to(CoreScoreboardProvider.class);
    }
}
