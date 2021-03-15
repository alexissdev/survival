package dev.notcacha.survival.api.binder.cache;

import dev.notcacha.survival.api.binder.back.ModelBinderBackLayout;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.model.SavableModel;

public interface ModelCacheBinder<T extends SavableModel> extends ModelBinderBackLayout<T> {

    /**
     * Bind a {@link ModelCache} which will be temporary, after a certain time the objects are automatically deleted from the cache.
     */

    ModelCacheBinder<T> bindTemporary();

    /**
     * Bind a {@link ModelCache} which will be default cache.
     */

    ModelCacheBinder<T> bindDefault();

}
