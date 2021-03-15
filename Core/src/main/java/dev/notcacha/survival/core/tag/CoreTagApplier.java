package dev.notcacha.survival.core.tag;

import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.tag.applier.TagApplier;
import dev.notcacha.survival.api.util.Colorize;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CoreTagApplier implements TagApplier {

    private static final String TEAM_FORMAT = "tag_%s_%s";

    @Inject
    private ModelMatcher<Tag> tagModelMatcher;

    @Override
    public void apply(Player player, String tagId) {

        Scoreboard playerScoreboard = player.getScoreboard();

        tagModelMatcher.findModelById(tagId).callback(tagCallback -> {

            Optional<Tag> tagOptionalResponse = tagCallback.getResponse();

            if (!tagOptionalResponse.isPresent()) {
                throw new IllegalArgumentException("Invalid id from tag.");
            }

            Tag tag = tagOptionalResponse.get();

            Team team = playerScoreboard.getTeam(String.format(TEAM_FORMAT, tag.getId(), player.getName()));

            if (team == null) {
                team = playerScoreboard.registerNewTeam(String.format(TEAM_FORMAT, tag.getId(), player.getName()));
            }

            team.setPrefix(
                    Colorize.colorize(
                            tag.getPrefix() + " &" + tag.getColorCodeFromPlayerName()
                    )
            );
            team.setSuffix(
                    Colorize.colorize(
                            " " + tag.getSuffix()
                    )
            );

            team.addEntry(player.getName());
        });
    }
}
