package dev.notcacha.survival.core.util;

import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.api.util.Validate;

public class PlayerStatisticPlaceholderApplier {

    /**
     * Apply the {@link Statistics} placeholders from text
     *
     * @param user has been get statistic
     * @param text where the placeholders will be set
     * @return The text and placeholders apply.
     */

    public static String apply(String text, User user) {
        Validate.nonNull(text, "The text of Player Statistic Placeholder Applier is null.");
        Validate.nonNull(user, "The user of Player Statistic Placeholder Applier is null.");

        //TODO:
        String placeholder = (text.startsWith("%player_")) ? text : "%player_" + text + "%";

        switch (placeholder.toLowerCase()) {

            case "balance": {
                return String.valueOf(user.getStatisticHandler().getBalance().getAmount());
            }

            case "kills": {
                return String.valueOf(user.getStatisticHandler().getKills().getAmount());
            }

            case "deaths": {
                return String.valueOf(user.getStatisticHandler().getDeaths().getAmount());
            }

            case "ores_emerald": {
                return String.valueOf(user.getStatisticHandler().getOres().getEmeraldStatistic());
            }

            case "ores_diamond": {
                return String.valueOf(user.getStatisticHandler().getOres().getDiamondStatistic());
            }

            case "ores_redstone": {
                return String.valueOf(user.getStatisticHandler().getOres().getRedstoneStatistic());
            }

            case "ores_gold": {
                return String.valueOf(user.getStatisticHandler().getOres().getGoldStatistic());
            }

            case "ores_iron": {
                return String.valueOf(user.getStatisticHandler().getOres().getIronStatistic());
            }

            case "ores_coal": {
                return String.valueOf(user.getStatisticHandler().getOres().getCoalStatistic());
            }

            default: {
                return null;
            }
        }
    }
}
