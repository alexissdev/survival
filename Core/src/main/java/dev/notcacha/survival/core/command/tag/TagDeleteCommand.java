package dev.notcacha.survival.core.command.tag;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.tag.Tag;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.Optional;

@Command(names = {"delete", "remove"}, permission = "survival.tag.remove")
public class TagDeleteCommand implements CommandClass {

    private final MessageHandler messageHandler;
    private final ModelCache<Tag> tagModelCache;
    private final ModelProcessor<Tag> tagModelProcessor;

    @Inject
    public TagDeleteCommand(MessageHandler messageHandler, ModelCache<Tag> tagModelCache, @Named("delete") ModelProcessor<Tag> tagModelProcessor) {
        this.messageHandler = messageHandler;
        this.tagModelCache = tagModelCache;
        this.tagModelProcessor = tagModelProcessor;
    }

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String tagId) {
        if (tagId == null) {
            messageHandler.send(player, "tag.delete.usage");

            return true;
        }

        Optional<Tag> tagOptional = tagModelCache.findIfPresent(tagId);

        if (!tagOptional.isPresent()) {
            messageHandler.sendReplacing(player, "default", "tag.not-exists", "%tag_id%", tagId);

            return true;
        }

        tagModelProcessor.process(tagOptional.get());

        messageHandler.sendReplacing(player, "default", "tag.delete.message", "%tag_id%", tagId);
        return true;
    }
}
