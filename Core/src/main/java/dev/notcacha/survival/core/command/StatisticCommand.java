package dev.notcacha.survival.core.command;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.core.util.PlayerStatisticPlaceholderApplier;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.Optional;

public class StatisticCommand implements CommandClass {

    private final ModelCache<User> userModelCache;
    private final MessageHandler messageHandler;

    @Inject
    public StatisticCommand(ModelCache<User> userModelCache, MessageHandler messageHandler) {
        this.userModelCache = userModelCache;
        this.messageHandler = messageHandler;
    }

    @Command(names = "stats")
    public boolean main(@Sender Player player, @OptArg OfflinePlayer target) {

        if (target == null) {
            //TODO: Send the yourself statistics.

            messageHandler.send(player, "statistic.message");
            return true;
        }

        Optional<User> targetUserOptional = userModelCache.findIfPresent(target.getUniqueId().toString());

        if (!targetUserOptional.isPresent()) {
            //TODO: The target is offline.

            messageHandler.sendReplacing(player, "default_mode", "target-offline", "%target_name%", target.getName());
            return true;
        }

        //TODO: Send target statistics from player.
         User targetUser = targetUserOptional.get();

        player.sendMessage(
                PlayerStatisticPlaceholderApplier.apply(
                        messageHandler.getMessage("statistic.message"),
                        targetUser
                )
        );
        return true;
    }
}
