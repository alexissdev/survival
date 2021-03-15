package dev.notcacha.survival.core.processor;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.storage.ModelStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteModelProcessor<T extends SavableModel> implements ModelProcessor<T> {

    private final ModelCache<T> modelCache;
    private final ModelStorage<T> modelStorage;

    @Inject
    public DeleteModelProcessor(ModelCache<T> modelCache, ModelStorage<T> modelStorage) {
        this.modelCache = modelCache;
        this.modelStorage = modelStorage;
    }

    /**
     * Start the delete processor.
     * <p>
     * Delete {@link T} model from storage and cache.
     */

    @Override
    public void process(T model) {
        modelCache.removeObject(model.getId());
        modelStorage.delete(model.getId());
    }
}
