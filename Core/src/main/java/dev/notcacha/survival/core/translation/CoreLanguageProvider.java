package dev.notcacha.survival.core.translation;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.user.User;
import me.yushust.message.language.Linguist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.logging.Level;

public class CoreLanguageProvider implements Linguist<Player> {

    @Inject
    private ModelCache<User> userModelCache;

    @Override
    public @Nullable String getLanguage(Player player) {
        User user = userModelCache.findIfPresent(player.getUniqueId().toString()).orElse(null);

        if (user == null) {
            Bukkit.getLogger().log(Level.SEVERE, "Error while finding user data for " + player.getName());
            return null;
        }

        return user.getLanguage();
    }

}
