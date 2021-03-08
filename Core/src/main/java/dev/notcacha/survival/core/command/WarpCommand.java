package dev.notcacha.survival.core.command;

import dev.notcacha.survival.core.command.warp.WarpCreateCommand;
import dev.notcacha.survival.core.command.warp.WarpDeleteCommand;
import dev.notcacha.survival.core.command.warp.WarpListCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "warp")
@SubCommandClasses({
        WarpCreateCommand.class,
        WarpDeleteCommand.class,
        WarpListCommand.class
})
public class WarpCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player) {
        messageHandler.send(player, "warp.main");
        return true;
    }
}
