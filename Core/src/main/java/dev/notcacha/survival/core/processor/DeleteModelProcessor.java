package dev.notcacha.survival.core.processor;

import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.storage.ModelStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteModelProcessor<T extends SavableModel> implements ModelProcessor<T> {

    @Inject
    private ObjectCache<T> objectCache;
    @Inject
    private ModelStorage<T> modelStorage;

    /**
     * Start the delete processor.
     * <p>
     * Delete {@link T} model from storage and cache.
     */

    @Override
    public void process(T model) {
        objectCache.removeObject(model.getId());

        modelStorage.delete(model.getId());
    }
}