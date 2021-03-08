package dev.notcacha.survival.core.command;

import dev.notcacha.survival.api.spawn.SpawnManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "setspawn", permission = "survival.spawn.set")
public class SetSpawnCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private SpawnManager spawnManager;

    @Command(names = "")
    public boolean setSpawn(@Sender Player player) {
        spawnManager.setSpawnLocation(player.getLocation());

        messageHandler.send(player, "spawn.set");

        return true;
    }
}
