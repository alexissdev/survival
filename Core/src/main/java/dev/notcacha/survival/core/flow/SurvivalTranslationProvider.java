package dev.notcacha.survival.core.flow;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.DefaultMapTranslationProvider;
import me.yushust.message.MessageHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SurvivalTranslationProvider extends DefaultMapTranslationProvider {

    private final MessageHandler messageHandler;

    @Inject
    public SurvivalTranslationProvider(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {

        if (key.equals("command.no-permission")) {
            Player player = (Player) namespace.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);

           return messageHandler.get(player, "no-permissions");
        }

        return super.getTranslation(namespace, key);
    }

}
