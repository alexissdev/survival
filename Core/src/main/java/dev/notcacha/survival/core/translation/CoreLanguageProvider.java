package dev.notcacha.survival.core.translation;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.user.User;
import me.yushust.message.language.Linguist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.logging.Level;

public class CoreLanguageProvider implements Linguist<Player> {

    @Inject
    private ObjectCache<User> userObjectCache;

    @Override
    public @Nullable String getLanguage(Player player) {
        User user;

        try {
            user = userObjectCache.findIfPresent(player.getUniqueId().toString()).orElse(null);
        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Error while finding user data for " + player.getName(), ex);
            return null;
        }

        if (user == null) {
            return null;
        }

        return user.getLanguage();
    }

}
