package dev.notcacha.survival.core.user.builder;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.api.user.builder.UserBuilder;
import dev.notcacha.survival.api.user.builder.properties.UserPropertiesBuilder;
import dev.notcacha.survival.api.user.builder.statistic.UserStatisticBuilder;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.user.CoreUser;
import org.jetbrains.annotations.Nullable;

public class CoreUserBuilder implements UserBuilder {

    private final String id;
    private String username;
    private String language;

    private CoreUserStatisticBuilder userStatisticBuilder;
    private CoreUserPropertiesBuilder userPropertiesBuilder;

    public CoreUserBuilder(String id) {
        this.id = Validate.nonNull(id, "The id from UserBuilder is null.");;

        this.username = null;
        this.language = "en";
    }

    @Override
    public UserBuilder setUsername(String username) {
        this.username = Validate.nonNull(username, "The username from UserBuilder is null.");

        return this;
    }

    @Override
    public UserBuilder setLanguage(String language) {
        this.language = Validate.nonNull(language, "The language from UserBuilder is null.");;
        return this;
    }

    @Override
    public UserStatisticBuilder buildStatistics() {
        if (userStatisticBuilder == null) {
            userStatisticBuilder = new CoreUserStatisticBuilder(this);
        }

        return userStatisticBuilder;
    }

    @Override
    public UserPropertiesBuilder buildProperties() {
        if (userPropertiesBuilder == null) {
            userPropertiesBuilder = new CoreUserPropertiesBuilder(this);
        }

        return userPropertiesBuilder;
    }

    @Override
    public User build() {
        return new CoreUser(
                id,
                username,
                language,
                new User.StatisticHandler() {
                    @Override
                    public Statistics getBalance() {
                        return userStatisticBuilder.statisticsMap.get(UserStatisticBuilder.Type.BALANCE);
                    }

                    @Override
                    public Statistics getKills() {
                        return userStatisticBuilder.statisticsMap.get(UserStatisticBuilder.Type.KILLS);
                    }

                    @Override
                    public Statistics getDeaths() {
                        return userStatisticBuilder.statisticsMap.get(UserStatisticBuilder.Type.DEATHS);
                    }

                    @Override
                    public UserOresStatistic getOres() {
                        return userStatisticBuilder.oresStatistic;
                    }
                },
                new User.BackpackHandler() {

                    private Backpack backpack = (Backpack) userPropertiesBuilder.objectMap.get("backpack");

                    @Override
                    public @Nullable Backpack getBackpack() {
                        return backpack;
                    }

                    @Override
                    public void setBackpack(Backpack backpack) {
                        this.backpack = backpack;
                    }
                },
                new User.TagCompound() {

                    private String tag = (String) userPropertiesBuilder.objectMap.get("tag");

                    @Override
                    public @Nullable String getTag() {
                        return tag;
                    }

                    @Override
                    public void setTag(String tag) {
                        this.tag = tag;
                    }
                }
        );
    }

    public static UserBuilder newBuilder(String id) {
        return new CoreUserBuilder(id);
    }
}
