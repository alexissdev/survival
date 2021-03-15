package dev.notcacha.survival.core.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "tag", permission = "survival.tag")
public class TagCommand implements CommandClass {

    private final MessageHandler messageHandler;

    @Inject
    public TagCommand(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Command(names = "")
    public boolean tag(@Sender Player player) {
        messageHandler.send(player, "tag.usage");
        return true;
    }
}
