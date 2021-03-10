package dev.notcacha.survival.core.listener.user;

import dev.notcacha.survival.api.event.user.UserJoinEvent;
import dev.notcacha.survival.api.scoreboard.ScoreboardProvider;
import dev.notcacha.survival.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.survival.api.tag.applier.TagApplier;
import dev.notcacha.survival.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class UserJoinListener implements Listener {

    @Inject
    private ScoreboardProvider scoreboardProvider;
    @Inject
    private TagApplier tagApplier;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onUserJoin(UserJoinEvent event) {
        Player player = event.getPlayer();

        scoreboardProvider.setup(player, ScoreboardProperties.Type.DEFAULT);

        User.TagCompound userTagCompound = event.getUser().getTagCompound();

        if (userTagCompound.getTag() !=  null) {
            tagApplier.apply(player, userTagCompound.getTag());
        }

    }
}
