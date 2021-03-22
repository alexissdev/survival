package dev.notcacha.survival.api.user.builder.statistic;

import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.user.builder.UserBackBuilder;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;

public interface UserStatisticBuilder extends UserBackBuilder {

    /**
     * The type's from statistic's.
     */

    enum Type {
        KILLS,
        DEATHS,
        BALANCE
    }

    /**
     * Set the statistic
     *
     * @param statisticType type from statistic.
     * @param statistics    has been set.
     */

    UserStatisticBuilder setStatistic(Type statisticType, Statistics statistics);

    /**
     * Set the ores statistic from user.
     */

    UserStatisticBuilder setOresStatistic(UserOresStatistic oresStatistic);

}
