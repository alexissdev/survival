package dev.notcacha.survival.core.translation.placeholder;

import me.yushust.message.format.PlaceholderProvider;
import me.yushust.message.track.ContextRepository;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class PlayerPlaceholderProvider implements PlaceholderProvider<Player> {

    @Override
    public @Nullable Object replace(ContextRepository contextRepository, Player player, String text) {
        if ("name".equals(text)) {
            return player.getName();
        }

        return null;
    }
}
