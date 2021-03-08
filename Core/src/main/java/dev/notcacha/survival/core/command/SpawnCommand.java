package dev.notcacha.survival.core.command;

import dev.notcacha.survival.api.spawn.SpawnManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "spawn", permission = "survival.spawn")
public class SpawnCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private SpawnManager spawnManager;

    @Command(names = "")
    public boolean spawn(@Sender Player player) {
        Location location = spawnManager.getSpawnLocation();

        if (location == null) {
            messageHandler.send(player, "spawn.not-exists");
            return true;
        }

        messageHandler.send(player, "spawn.teleport");
        player.teleport(location);
        return true;
    }
}
