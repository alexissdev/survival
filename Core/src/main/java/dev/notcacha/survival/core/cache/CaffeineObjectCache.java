package dev.notcacha.survival.core.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.concurrent.AsyncResponse;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.storage.ModelStorage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

@Singleton
public class CaffeineObjectCache<T extends SavableModel> implements ObjectCache<T> {

    private final LoadingCache<String, T> objectCache;

    @Inject
    public CaffeineObjectCache(ModelStorage<T> modelStorage) {
        objectCache = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(Duration.of(5, ChronoUnit.MINUTES))
                .softValues()
                .<String, T>removalListener((key, value, cause) -> modelStorage.save(value))
                .build(modelId -> {
                    List<T> modelList = new ArrayList<>(
                            Collections.singletonList(
                                    findIfPresent(modelId).orElse(null)
                            )
                    );

                    if (modelList.get(0) == null) {
                        modelStorage.findOne(modelId).callback(callback -> {
                            Optional<T> modelResponse = callback.getResponse();

                            modelResponse.ifPresent(t -> modelList.add(0, t));
                        });
                    }

                    return modelList.get(0);
                });
    }

    @Override
    public Optional<T> findIfPresent(String objectId) {
        return Optional.ofNullable(
                objectCache.get(objectId)
        );
    }

    @Override
    public void addObject(T object) {
        objectCache.put(object.getId(), object);
    }

    @Override
    public void removeObject(String objectId) {
        objectCache.invalidate(objectId);
    }

    @Override
    public boolean ifPresent(String objectId) {
        return findIfPresent(objectId).isPresent();
    }

    @Override
    public Set<T> getAllPresent() {
        return new HashSet<>(objectCache.asMap().values());
    }

}