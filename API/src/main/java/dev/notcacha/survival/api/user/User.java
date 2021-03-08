package dev.notcacha.survival.api.user;

import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;

public interface User extends SavableModel {

    /**
     * @return The username from this user.
     */

    String getUsername();

    /**
     * @return The language from this user.
     */

    String getLanguage();

    /**
     * Set the language from this user.
     */

    void setLanguage(String language);

    /**
     * @return The balance from this user.
     */

    Statistics getBalance();

    /**
     * @return The kills statistic from this user.
     */

    Statistics getKills();

    /**
     * @return The deaths statistic from this user.
     */

    Statistics getDeaths();

    /**
     * @return The ores statistic from this user.
     */

    UserOresStatistic getOres();
}
