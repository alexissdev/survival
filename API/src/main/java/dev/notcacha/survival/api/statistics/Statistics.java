package dev.notcacha.survival.api.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Statistics {

    /**
     * @return The amount statistic.
     */

    @JsonProperty("statistic_amount")
    int getAmount();

    /**
     * Set the amount from statistic.
     */

    void setAmount(int amount);

    /**
     * Increase the statistic amount.
     */

    void increase();

    /**
     * Decrease the statistic amount.
     */

    void decrease();

}
