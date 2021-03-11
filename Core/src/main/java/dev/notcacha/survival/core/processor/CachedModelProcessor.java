package dev.notcacha.survival.core.processor;

import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.exception.ProcessorException;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelProcessor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CachedModelProcessor<T extends SavableModel> implements ModelProcessor<T> {

    @Inject
    private ModelCache<T> modelCache;

    /**
     * Add model to cache.
     *
     * @param model has been added to cached.
     * <p>
     * In any case that the model already exists in cache, an exception of type {@link ProcessorException} will be sent so that it can be cached and be able to respond to this obstacle.
     */

    @Override
    public void process(T model) {
        if (modelCache.ifPresent(model.getId())) {
            throw new ProcessorException("[CachedModelProcessor] The model id " + model.getId() + " already in cached.");
        }

        modelCache.addObject(model);
    }
}
