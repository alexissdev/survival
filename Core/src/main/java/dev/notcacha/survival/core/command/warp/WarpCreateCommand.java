package dev.notcacha.survival.core.command.warp;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.warp.Warp;
import dev.notcacha.survival.api.warp.creator.WarpCreator;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"create", "add", "c"}, permission = "survival.warp.create")
public class WarpCreateCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private ObjectCache<Warp> warpObjectCache;

    @Command(names = "")
    public boolean create(@Sender Player player, @OptArg String warpName) {

        if (warpName == null) {
            //TODO: send the usage message.

            messageHandler.send(player, "warp.create.usage");
            return true;
        }

        if (warpObjectCache.ifPresent(warpName)) {
            //TODO: the warp exists.

            messageHandler.sendReplacing(player, "warp.exists", "%warp_name%", warpName);
            return true;
        }

        //TODO: create warp

        Warp warp = WarpCreator.create(warpName);

        //TODO: add warp to cache and storage.
        warpObjectCache.addObject(warp);

        //TODO: send create message from sender.
        messageHandler.sendReplacing(player, "default", "warp.create.message", "%warp_name%", warpName);
        return true;
    }

}
