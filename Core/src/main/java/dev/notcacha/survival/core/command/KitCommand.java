package dev.notcacha.survival.core.command;

import dev.notcacha.survival.core.command.kit.KitCreateCommand;
import dev.notcacha.survival.core.command.kit.KitDeleteCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "kit")
@SubCommandClasses({
        KitCreateCommand.class,
        KitDeleteCommand.class
})
public class KitCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;

    @Command(names = "")
    public boolean kit(@Sender Player player) {
        messageHandler.send(player, "kit.usage");
        return true;
    }

}
