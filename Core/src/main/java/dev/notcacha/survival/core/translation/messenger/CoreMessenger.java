package dev.notcacha.survival.core.translation.messenger;

import me.yushust.message.send.MessageSender;
import org.bukkit.entity.Player;

public class CoreMessenger implements MessageSender<Player> {

    @Override
    public void send(Player player, String mode, String message) {
        player.sendMessage(message);
    }
}
