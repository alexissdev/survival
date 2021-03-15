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

    private final MessageHandler messageHandler;
    private final SpawnManager spawnManager;

    @Inject
    public SetSpawnCommand(MessageHandler messageHandler, SpawnManager spawnManager) {
        this.messageHandler = messageHandler;
        this.spawnManager = spawnManager;
    }

    @Command(names = "")
    public boolean setSpawn(@Sender Player player) {
        spawnManager.setSpawnLocation(player.getLocation());

        messageHandler.send(player, "spawn.set");

        return true;
    }
}
