package dev.notcacha.survival.core.processor;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelDeleteProcessor;
import dev.notcacha.survival.api.storage.ModelStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoreModelDeleteProcessor<T extends SavableModel> implements ModelDeleteProcessor<T> {

    @Inject
    private ObjectCache<T> objectCache;
    @Inject
    private ModelStorage<T> modelStorage;

    @Override
    public void process(T model) {
        objectCache.removeObject(model.getId());

        modelStorage.delete(model.getId());
    }
}
