package dev.notcacha.survival.core.command.warp;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = {"list", "l"})
public class WarpListCommand implements CommandClass {

    @Command(names = "")
    public boolean list(@Sender Player player) {

        //Create the gui and add all warps.

        return true;
    }

}
