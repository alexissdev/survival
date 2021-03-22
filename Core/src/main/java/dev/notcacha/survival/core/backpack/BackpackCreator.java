package dev.notcacha.survival.core.backpack;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.item.SerializableItem;

import java.util.HashMap;
import java.util.Map;

public class BackpackCreator {

    private static final int DEFAULT_ROWS = 54;

    /**
     * @see dev.notcacha.survival.api.backpack.creator.BackpackCreator#create(int)
     */

    public static Backpack create() {
        return create(DEFAULT_ROWS);
    }

    /**
     * @see dev.notcacha.survival.api.backpack.creator.BackpackCreator#create(int, Map)
     */

    public static Backpack create(int rows) {
        return create(rows, new HashMap<>());
    }

    /**
     * Create the backpack object
     *
     * @param rows     from backpack.
     * @param contents from backpack.
     * @return New {@link Backpack} object.
     */

    public static Backpack create(int rows, Map<Integer, SerializableItem> contents) {
        return new CoreBackpack(
                rows,
                contents
        );
    }

}