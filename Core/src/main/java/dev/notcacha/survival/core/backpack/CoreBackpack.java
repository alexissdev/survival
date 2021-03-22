package dev.notcacha.survival.core.backpack;

import dev.notcacha.survival.api.backpack.Backpack;
import dev.notcacha.survival.api.item.SerializableItem;

import java.util.Map;

public class CoreBackpack implements Backpack {

    private final int size;
    private final Map<Integer, SerializableItem> contents;

    public CoreBackpack(int size, Map<Integer, SerializableItem> contents) {
        this.size = size;
        this.contents = contents;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Map<Integer, SerializableItem> getContents() {
        return contents;
    }
}
