package dev.notcacha.survival.core.command.tag;

import dev.notcacha.survival.core.command.tag.editor.TagPrefixEditorCommand;
import dev.notcacha.survival.core.command.tag.editor.TagSuffixEditorCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"editor", "edit"}, permission = "tag.editor")
@SubCommandClasses({
        TagPrefixEditorCommand.class,
        TagSuffixEditorCommand.class
})
public class TagEditorCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean editor(@Sender Player player) {
        messageHandler.send(player, "tag.editor.main");
        return true;
    }
}
