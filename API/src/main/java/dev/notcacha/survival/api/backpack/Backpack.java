package dev.notcacha.survival.api.backpack;

import dev.notcacha.survival.api.item.SerializableItem;

import java.util.Map;

public interface Backpack {

    /**
     * @return The backpack size.
     */

    int getSize();

    /**
     * @return The backpack contents.
     */

    Map<Integer, SerializableItem> getContents();

}
