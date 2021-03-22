package dev.notcacha.survival.api.user.builder;

import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.api.user.builder.properties.UserPropertiesBuilder;
import dev.notcacha.survival.api.user.builder.statistic.UserStatisticBuilder;

public interface UserBuilder {

    /**
     * Set the username from user.
     *
     * @param username has been set.
     */

    UserBuilder setUsername(String username);

    /**
     * Set the language from user.
     *
     * @param language has been set.
     */

    UserBuilder setLanguage(String language);

    /**
     * Build the statistic from this user.
     *
     * @return instance from {@link UserStatisticBuilder}
     */

    UserStatisticBuilder buildStatistics();

    /**
     * Build the properties from this user.
     *
     * @return instance from {@link UserPropertiesBuilder}
     */

    UserPropertiesBuilder buildProperties();

    /**
     * @return New {@link User} using this properties.
     */

    User build();
}
