package dev.notcacha.survival.api.statistics.creator;

import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.util.Validate;

public interface StatisticsCreator {

    @FunctionalInterface
    interface StatisticsProperties {

        /**
         * @return The amount.
         */

        int getAmount();

    }

    /**
     * @see StatisticsCreator#create(StatisticsProperties)
     */

    static Statistics create() {
        return create(() -> 0);
    }

    /**
     * Create the statistic from user.
     *
     * @return New {@link Statistics} instance.
     */

    static Statistics create(StatisticsProperties properties) {
        Validate.nonNull(properties, "The user statistic properties from user statistic creator is null.");

        return new Statistics() {

            private int amount = properties.getAmount();

            @Override
            public int getAmount() {
                return amount;
            }

            @Override
            public void setAmount(int amount) {
                this.amount = amount;
            }

            @Override
            public void increase() {
                amount++;
            }

            @Override
            public void decrease() {
                amount--;
            }
        };
    }

}
