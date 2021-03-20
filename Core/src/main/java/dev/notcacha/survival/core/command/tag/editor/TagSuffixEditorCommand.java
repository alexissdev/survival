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

@Command(names = {"suffix", "s"}, permission = "tag.editor.suffix")
public class TagSuffixEditorCommand implements CommandClass {

    private final MessageHandler messageHandler;
    private final ModelMatcher<Tag> tagModelMatcher;

    @Inject
    public TagSuffixEditorCommand(MessageHandler messageHandler, ModelMatcher<Tag> tagModelMatcher) {
        this.messageHandler = messageHandler;
        this.tagModelMatcher = tagModelMatcher;
    }

    @Command(names = "")
    public boolean suffix(@Sender Player player, @Named("id") String tagId, @Named("suffix") String suffix) {

        tagModelMatcher.findModelById(tagId).whenComplete((object, ignore) -> {

            if (!object.isPresent()) {
                messageHandler.sendReplacing(player, null, "tag.not-exists", "%tag_id%", tagId);
                return;
            }

            Tag tag = object.get();

            if (tag.getSuffix().equals(suffix)) {
                messageHandler.sendReplacing(player, null, "tag.editor.set.already.suffix", "%tag_suffix%", suffix);
                return;
            }

            tag.setSuffix(suffix);

            messageHandler.sendReplacing(player, null, "tag.editor.set.suffix", "%tag_id%", tagId, "%tag_suffix%", suffix);
        });


        return true;
    }

}
