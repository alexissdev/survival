package dev.notcacha.survival.core.command.tag;

import dev.notcacha.survival.api.exception.ProcessorException;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.tag.creator.TagCreator;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;

@Command(names = {"create", "add"}, permission = "survival.tag.create")
public class TagCreateCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    @Named("cached")
    private ModelProcessor<Tag> cachedModelProcessor;

    @Command(names = "")
    public boolean create(@Sender Player player, @OptArg String tagId) {
        if (tagId == null) {
            messageHandler.send(player, "tag.create.usage");

            return true;
        }

        Tag tag = TagCreator.create(TagCreator.TagProperties.ofArray(tagId));

        try  {
            cachedModelProcessor.process(tag);
        } catch (ProcessorException ignored) {
            messageHandler.sendReplacing(player, "default", "tag.exists", "%tag_id%", tagId);
            return true;
        }

        messageHandler.sendReplacing(player, "default", "tag.create.message","%tag_id%", tagId);
        return true;
    }
}
