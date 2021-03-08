package dev.notcacha.survival.core.command.warp;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.warp.Warp;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"delete", "remove", "d", "r"}, permission = "survival.warp.delete")
public class WarpDeleteCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private ObjectCache<Warp> warpObjectCache;

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String warpName) {
        if (warpName == null) {
            messageHandler.send(player, "delete.usage");

            return true;
        }

        if (!warpObjectCache.ifPresent(warpName)) {
            messageHandler.send(player, "warp.not.exists");

            return true;
        }

        messageHandler.sendReplacing(player, "default", "warp.delete.message", "%warp_name%", warpName);

        warpObjectCache.removeObject(warpName);
        return true;
    }

}
