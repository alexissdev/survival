package dev.notcacha.survival.core.command.kit;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"edit", "e"}, permission = "survival.kit.edit")
public class KitEditCommand implements CommandClass {

    private final MessageHandler messageHandler;

    @Inject
    public KitEditCommand(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Command(names = "")
    public boolean edit(@Sender Player player, @OptArg String kitId) {
        if (kitId == null) {
            messageHandler.send(player, "kit.edit.usage");

            return true;
        }


        return true;
    }

}
