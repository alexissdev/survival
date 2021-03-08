package dev.notcacha.survival.api.user.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.statistics.creator.StatisticsCreator;

public interface UserOresStatistic {

    /**
     * @return The emerald statistic.
     */

    @JsonProperty("emerald_statistic")
    Statistics getEmeraldStatistic();

    /**
     * @return The diamond statistic.
     */

    @JsonProperty("diamond_statistic")
    Statistics getDiamondStatistic();

    /**
     * @return The redstone statistic.
     */

    @JsonProperty("redstone_statistic")
    Statistics getRedstoneStatistic();

    /**
     * @return The gold statistic.
     */

    @JsonProperty("gold_statistic")
    Statistics getGoldStatistic();

    /**
     * @return The iron statistic.
     */

    @JsonProperty("iron_statistic")
    Statistics getIronStatistic();

    /**
     * @return The coal statistic.
     */

    @JsonProperty("coal_statistic")
    Statistics getCoalStatistic();

    /**
     * @return New {@link UserOresStatistic} instance.
     */

    static UserOresStatistic create() {
        return new UserOresStatistic() {

            private final Statistics emeraldStatistic = StatisticsCreator.create();
            private final Statistics diamondStatistic = StatisticsCreator.create();
            private final Statistics redstoneStatistic = StatisticsCreator.create();
            private final Statistics goldStatistic = StatisticsCreator.create();
            private final Statistics ironStatistic = StatisticsCreator.create();
            private final Statistics coalStatistic = StatisticsCreator.create();

            @Override
            public Statistics getEmeraldStatistic() {
                return emeraldStatistic;
            }

            @Override
            public Statistics getDiamondStatistic() {
                return diamondStatistic;
            }

            @Override
            public Statistics getRedstoneStatistic() {
                return redstoneStatistic;
            }

            @Override
            public Statistics getGoldStatistic() {
                return goldStatistic;
            }

            @Override
            public Statistics getIronStatistic() {
                return ironStatistic;
            }

            @Override
            public Statistics getCoalStatistic() {
                return coalStatistic;
            }
        };
    }
}
