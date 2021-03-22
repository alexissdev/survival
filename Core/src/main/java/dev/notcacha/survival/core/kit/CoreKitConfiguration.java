package dev.notcacha.survival.core.kit;

import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.util.Validate;

public class CoreKitConfiguration implements Kit.Configuration {

    private String permission;

    @Deprecated
    public CoreKitConfiguration() {
        this("survival.kit.empty");
    }

    public CoreKitConfiguration(String permission) {
        this.permission = Validate.nonNull(permission, "The permission from KitConfiguration is null.");
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
