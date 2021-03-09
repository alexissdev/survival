package dev.notcacha.survival.core.kit.creator;

public class KitCreatorSettings {

    private final boolean useInventoryContents;
    private final boolean useArmorContents;

    public KitCreatorSettings(boolean useInventoryContents, boolean useArmorContents) {
        this.useInventoryContents = useInventoryContents;
        this.useArmorContents = useArmorContents;
    }

    public boolean isUseInventoryContents() {
        return useInventoryContents;
    }

    public boolean isUseArmorContents() {
        return useArmorContents;
    }
}
