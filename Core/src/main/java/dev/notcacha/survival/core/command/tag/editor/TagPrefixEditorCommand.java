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

@Command(names = {"prefix", "p"}, permission = "tag.editor.prefix")
public class TagPrefixEditorCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private ModelMatcher<Tag> tagModelMatcher;

    @Command(names = "")
    public boolean prefix(@Sender Player player, @Named("id") String tagId, @Named("prefix") String prefix) {

        tagModelMatcher.findModelById(tagId).whenComplete((object, ignore) -> {

            if (!object.isPresent()) {
                messageHandler.sendReplacing(player, null, "tag.not-exists", "%tag_id%", tagId);
                return;
            }

            Tag tag = object.get();

            if (tag.getPrefix().equals(prefix)) {
                messageHandler.sendReplacing(player, null, "tag.editor.set.already.prefix", "%tag_prefix%", prefix);
                return;
            }

            tag.setPrefix(prefix);

            messageHandler.sendReplacing(player, null, "tag.editor.set.prefix", "%tag_id%", tagId, "%tag_prefix%", prefix);
        });

        return true;
    }
}
