package dev.notcacha.survival.core.cache;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.model.SavableModel;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Singleton
public class MapModelCache<T extends SavableModel> implements ModelCache<T> {

    private final Map<String, T> modelMap = new HashMap<>();

    @Override
    public Optional<T> findIfPresent(String objectId) {
        return Optional.ofNullable(
                modelMap.get(objectId)
        );
    }

    @Override
    public void addObject(T object) {
        modelMap.put(object.getId(), object);
    }

    @Override
    public void removeObject(String objectId) {
        modelMap.remove(objectId);
    }

    @Override
    public boolean ifPresent(String objectId) {
        return modelMap.containsKey(objectId);
    }

    @Override
    public Set<T> getAllPresent() {
        return new HashSet<>(modelMap.values());
    }
}
