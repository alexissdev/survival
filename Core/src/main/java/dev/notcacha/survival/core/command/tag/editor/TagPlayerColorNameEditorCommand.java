package dev.notcacha.survival.core.command.tag.editor;

import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.tag.Tag;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"namecolor", "nc"}, permission = "tag.editor.namecolor")
public class TagPlayerColorNameEditorCommand implements CommandClass {

    private final MessageHandler messageHandler;
    private final ModelMatcher<Tag> tagModelMatcher;

    @Inject
    public TagPlayerColorNameEditorCommand(MessageHandler messageHandler, ModelMatcher<Tag> tagModelMatcher) {
        this.messageHandler = messageHandler;
        this.tagModelMatcher = tagModelMatcher;
    }

    @Command(names = "")
    public boolean nameColor(@Sender Player player, @Named("id") String tagId, @Named("namecolor") char color) {

        tagModelMatcher.findModelById(tagId).whenComplete((object, ignore) -> {

            if (!object.isPresent()) {
                messageHandler.sendReplacing(player, null, "tag.not-exists", "%tag_id%", tagId);
                return;
            }

            Tag tag = object.get();

            if (tag.getColorCodeFromPlayerName() == color) {
                messageHandler.sendReplacing(player, null, "tag.editor.set.already.color", "%tag_namecolor%", color);
                return;
            }

            tag.setColorCodeFromPlayerName(color);

            messageHandler.sendReplacing(player, null, "tag.editor.set.color", "%tag_id%", tagId, "%tag_namecolor%", color);
        });

        return true;
    }
}
