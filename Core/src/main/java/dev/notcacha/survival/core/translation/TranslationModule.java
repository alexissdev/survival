package dev.notcacha.survival.core.translation;

import dev.notcacha.survival.core.translation.interceptor.ArrowMessageInterceptor;
import dev.notcacha.survival.core.translation.interceptor.CenterMessageInterceptor;
import dev.notcacha.survival.core.translation.interceptor.ColorMessageInterceptor;
import dev.notcacha.survival.core.translation.messenger.CoreMessenger;
import dev.notcacha.survival.core.translation.placeholder.PlayerPlaceholderProvider;
import dev.notcacha.survival.core.translation.placeholder.PlayerStatisticPlaceholderProvider;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

public class TranslationModule extends AbstractModule {

    @Provides
    @Singleton
    public MessageHandler provideMessageProvider(Plugin plugin, CoreLanguageProvider languageProvider,
                                                 PlayerStatisticPlaceholderProvider playerStatisticPlaceholderProvider) {

        MessageSource source = MessageSourceDecorator
                .decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang_%lang%.yml"))
                .addFallbackLanguage("en")
                .get();

        MessageProvider messageProvider = MessageProvider.create(
                source,
                config -> {
                    config.specify(Player.class)
                            .setLinguist(languageProvider)
                            .setMessageSender(new CoreMessenger())
                            .addProvider("player", new PlayerPlaceholderProvider())
                            .addProvider("player", playerStatisticPlaceholderProvider);
                    config.intercept(new ColorMessageInterceptor())
                            .intercept(new CenterMessageInterceptor())
                            .intercept(new ArrowMessageInterceptor());
                }
        );

        return MessageHandler.of(messageProvider);
    }

}
