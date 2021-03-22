package dev.notcacha.survival.core.user.builder;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.user.builder.UserBuilder;
import dev.notcacha.survival.api.user.builder.properties.UserPropertiesBuilder;

import java.util.HashMap;
import java.util.Map;

public class CoreUserPropertiesBuilder implements UserPropertiesBuilder {

    final Map<String, Object> objectMap = new HashMap<>();

    private final UserBuilder back;

    public CoreUserPropertiesBuilder(UserBuilder back) {
        this.back = back;
    }

    @Override
    public UserPropertiesBuilder setBackpack(Backpack backpack) {

        objectMap.put("backpack", backpack);

        return this;
    }

    @Override
    public UserPropertiesBuilder setTag(String tag) {

        objectMap.put("tag", tag);

        return this;
    }

    @Override
    public UserBuilder back() {
        return back;
    }

}
