package dev.notcacha.survival.core.translation.placeholder;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.core.util.PlayerStatisticPlaceholderApplier;
import me.yushust.message.format.PlaceholderProvider;
import me.yushust.message.track.ContextRepository;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class PlayerStatisticPlaceholderProvider implements PlaceholderProvider<Player> {

    @Inject
    private ObjectCache<User> userObjectCache;

    @Override
    public @Nullable Object replace(ContextRepository contextRepository, Player player, String text) {
        Optional<User> userOptional = userObjectCache.findIfPresent(player.getUniqueId().toString());

        if (!userOptional.isPresent()) {
            return null;
        }

        User user = userOptional.get();

        return PlayerStatisticPlaceholderApplier.apply(text, user);
    }
}
