package dev.notcacha.survival.core.command.tag;

import dev.notcacha.survival.api.exception.ProcessorException;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.tag.creator.TagCreator;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"create", "add"}, permission = "survival.tag.create")
public class TagCreateCommand implements CommandClass {

    private final MessageHandler messageHandler;
    private final ModelProcessor<Tag> cachedModelProcessor;

    @Inject
    public TagCreateCommand(MessageHandler messageHandler, @Named("cached") ModelProcessor<Tag> cachedModelProcessor) {
        this.messageHandler = messageHandler;
        this.cachedModelProcessor = cachedModelProcessor;
    }

    @Command(names = "")
    public boolean create(@Sender Player player, @Named("id") String tagId) {
        Tag tag = TagCreator.create(TagCreator.TagProperties.ofArray(tagId));

        try  {
            cachedModelProcessor.process(tag);
        } catch (ProcessorException ignored) {
            messageHandler.sendReplacing(player, null, "tag.exists", "%tag_id%", tagId);
            return true;
        }

        messageHandler.sendReplacing(player, null, "tag.create","%tag_id%", tagId);
        return true;
    }
}
