package dev.notcacha.survival.core.user.builder;

import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.user.builder.UserBuilder;
import dev.notcacha.survival.api.user.builder.statistic.UserStatisticBuilder;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;

import java.util.HashMap;
import java.util.Map;

public class CoreUserStatisticBuilder implements UserStatisticBuilder {

    final Map<Type, Statistics> statisticsMap = new HashMap<>();

    UserOresStatistic oresStatistic;

    private final UserBuilder back;

    public CoreUserStatisticBuilder(UserBuilder back) {
        this.back = back;

        this.oresStatistic = null;
    }

    @Override
    public UserStatisticBuilder setStatistic(Type statisticType, Statistics statistics) {

        statisticsMap.put(statisticType, statistics);

        return this;
    }

    @Override
    public UserStatisticBuilder setOresStatistic(UserOresStatistic oresStatistic) {

        this.oresStatistic = oresStatistic;

        return this;
    }

    @Override
    public UserBuilder back() {
        return back;
    }
}
