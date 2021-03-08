package dev.notcacha.survival.api.user.creator;

import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.statistics.creator.StatisticsCreator;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;
import dev.notcacha.survival.api.util.Validate;
import org.bukkit.Bukkit;

import java.util.UUID;

public interface UserCreator {

    interface UserProperties {

        /**
         * @return The id has been set from user.
         */

        String getId();

        /**
         * @return The username has been set from user.
         */

        String getUsername();

        /**
         * Create UserProperties.
         *
         * @return New {@link UserProperties} instance.
         */

        static UserProperties create(String id, String username) {
            Validate.nonNull(id, "The id from user properties is null.");
            Validate.nonNull(username, "The username from user properties is null.");

            return new UserProperties() {
                @Override
                public String getId() {
                    return id;
                }

                @Override
                public String getUsername() {
                    return username;
                }
            };
        }

        static UserProperties ofOfflinePlayer(String id) {
            return create(
                    id,
                    Bukkit.getServer().getOfflinePlayer(
                            UUID.fromString(id)
                    ).getName()
            );
        }

    }

    /**
     * Create user.
     *
     * @return New {@link User} instace.
     */

    static User create(UserProperties properties) {
        Validate.nonNull(properties, "The user properties is null.");

        return new User() {

            private String language = "en";

            private final Statistics balanceStatistic = StatisticsCreator.create();
            private final Statistics killsStatistic = StatisticsCreator.create();
            private final Statistics deathsStatistic = StatisticsCreator.create();
            private final UserOresStatistic oresStatistic = UserOresStatistic.create();

            @Override
            public String getId() {
                return properties.getId();
            }

            @Override
            public String getUsername() {
                return properties.getUsername();
            }

            @Override
            public String getLanguage() {
                return language;
            }

            @Override
            public void setLanguage(String language) {
                this.language = language;
            }

            @Override
            public Statistics getBalance() {
                return balanceStatistic;
            }

            @Override
            public Statistics getKills() {
                return killsStatistic;
            }

            @Override
            public Statistics getDeaths() {
                return deathsStatistic;
            }

            @Override
            public UserOresStatistic getOres() {
                return oresStatistic;
            }

        };
    }

}
