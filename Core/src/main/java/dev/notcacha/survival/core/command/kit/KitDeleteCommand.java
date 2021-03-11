package dev.notcacha.survival.core.command.kit;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.processor.ModelProcessor;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Command(names = {"delete", "d", "remove", "r"}, permission = "survival.kit.delete")
public class KitDeleteCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private ModelCache<Kit> kitModelCache;
    @Inject
    @Named("delete")
    private ModelProcessor<Kit> kitModelDeleteProcessor;

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String kitId) {
        if (kitId == null) {
            messageHandler.send(player, "kit.delete.usage");

            return true;
        }

        Optional<Kit> kitOptional = kitModelCache.findIfPresent(kitId);

        if (!kitOptional.isPresent()) {
            messageHandler.sendReplacing(player, "default", "kit.not-exists", "%kit_id%", kitId);

            return true;
        }

        kitModelDeleteProcessor.process(kitOptional.get());
        messageHandler.sendReplacing(player, "default", "kit.delete.message", "%kit_id%", kitId);
        return true;
    }
}
