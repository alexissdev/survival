package dev.notcacha.survival.api.user.builder.properties;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.user.User.BackpackHandler;
import dev.notcacha.survival.api.user.User.TagCompound;
import dev.notcacha.survival.api.user.builder.UserBackBuilder;

public interface UserPropertiesBuilder extends UserBackBuilder {

    /**
     * Set the backpack from  {@link BackpackHandler}
     */

    UserPropertiesBuilder setBackpack(Backpack backpack);

    /**
     * Set the tag from {@link TagCompound}
     */

    UserPropertiesBuilder setTag(String tag);
}
