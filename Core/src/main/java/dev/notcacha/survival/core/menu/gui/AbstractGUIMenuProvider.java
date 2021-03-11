package dev.notcacha.survival.core.menu.gui;

import dev.notcacha.survival.core.menu.AbstractMenuProvider;
import org.bukkit.inventory.Inventory;
import team.unnamed.gui.core.gui.type.GUIBuilder;

public abstract class AbstractGUIMenuProvider extends AbstractMenuProvider {

    /**
     * @return a new {@link GUIBuilder} that will be transformed into a {@link Inventory}
     */

    public abstract GUIBuilder getGui();

    @Override
    public Inventory get() {
        GUIBuilder builder = getGui();

        if (builder == null) {
            throw new IllegalArgumentException("The gui builder from menu provider is null.");
        }

        return builder.build();
    }
}
