package dev.notcacha.survival.api.backpack.creator;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.item.SerializableItem;

import java.util.HashMap;
import java.util.Map;

public interface BackpackCreator {

    int DEFAULT_ROWS = 54;

    /**
     * @see BackpackCreator#create(int)
     */

    static Backpack create() {
        return create(DEFAULT_ROWS);
    }

    /**
     * @see BackpackCreator#create(int, Map)
     */

    static Backpack create(int rows) {
        return create(rows, new HashMap<>());
    }

    /**
     * Create the backpack object
     *
     * @param rows     from backpack.
     * @param contents from backpack.
     * @return New {@link Backpack} object.
     */

    static Backpack create(int rows, Map<Integer, SerializableItem> contents) {
        return new Backpack() {
            @Override
            public int getSize() {
                return rows;
            }

            @Override
            public Map<Integer, SerializableItem> getContents() {
                return contents;
            }
        };
    }

}
