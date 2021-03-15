package dev.notcacha.survival.api.binder;

import dev.notcacha.survival.api.binder.cache.ModelCacheBinder;
import dev.notcacha.survival.api.binder.processor.ModelProcessorBinder;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.Processor;
import dev.notcacha.survival.api.storage.ModelStorage;

public interface ModelBinder<T extends SavableModel> {

    /**
     * Bind a {@link ModelStorage} for model {@link T}
     *
     * @return This class instance.
     */

    ModelBinder<T> bindStorage();

    /**
     * Bind a {@link ModelMatcher} for model {@link T}
     *
     * @return This class instance.
     */

    ModelBinder<T> bindMatcher();

    /**
     * Bind the {@link ModelCache} from {@link T} model.
     *
     * @return New {@link ModelCacheBinder} from bind cache's.
     */

    ModelCacheBinder<T> bindCache();

    /**
     * Bind the {@link Processor} from {@link T} model.
     *
     * @return New {@link ModelProcessorBinder} from bind processor's.
     */

    ModelProcessorBinder<T> bindProcessors();

    /**
     * @return New binder from bind {@link M} model and class.
     */

    <M extends SavableModel> ModelBinder<M> newBinder(Class<M> modelClass);
}
