package dev.notcacha.survival.core.cache.map;

import dev.notcacha.survival.api.model.SavableModel;

import java.util.HashMap;

/**
 * This class is quite confusing, but it is simply to create a wrapper from model to a map
 * <p>
 * Example of use @Inject private ModelCache<ModelMap<Integer, SerializableItem>> serializableItemMapObjectCache;
 */

public class ModelMap<K, V> extends HashMap<K, V> implements SavableModel {

    private final String id;

    public ModelMap(String id) {
        super();
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
