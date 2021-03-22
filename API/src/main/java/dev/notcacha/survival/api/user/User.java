package dev.notcacha.survival.api.user;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.statistics.Statistics;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.user.statistics.UserOresStatistic;
import org.jetbrains.annotations.Nullable;

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
     * @return The handler from {@link Statistics}.
     */

    StatisticHandler getStatisticHandler();

    interface StatisticHandler {

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

    /**
     * @return the handle from user backpack.
     */

    BackpackHandler getBackpackHandler();

    interface BackpackHandler {

        /**
         * @return The backpack of this user.
         */

        @Nullable
        Backpack getBackpack();

        /**
         * Set the backpack of this user.
         */

        void setBackpack(Backpack backpack);

    }

    /**
     * @return the user tag compound
     */

    TagCompound getTagCompound();

    interface TagCompound {

        /**
         * @return The id from {@link Tag}.
         */

        @Nullable
        String getTag();

        /**
         * Set the tag id from this user.
         */

        void setTag(String tag);

    }

}
