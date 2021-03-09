package dev.notcacha.survival.api.binder;

import dev.notcacha.survival.api.binder.processor.ModelProcessorBinder;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.Processor;
import dev.notcacha.survival.api.storage.ModelStorage;

public interface ModelDataBinder<T extends SavableModel> {

    /**
     * Bind a {@link ModelStorage} for model {@link T}
     *
     * @return This class instance.
     */

    ModelDataBinder<T> bindStorage();

    /**
     * Bind a {@link ObjectCache} for model {@link T}
     *
     * @return This class instance.
     */

    ModelDataBinder<T> bindCache(ObjectCache.Type type);

    /**
     * Bind a {@link ModelMatcher} for model {@link T}
     *
     * @return This class instance.
     */

    ModelDataBinder<T> bindMatcher();

    /**
     * Bind the {@link Processor} from {@link T} model.
     *
     * @return New {@link ModelProcessorBinder} from bind processor's.
     */

    ModelProcessorBinder<T> bindProcessors();

    /**
     * @return New binder from bind {@link M} model and class.
     */

    <M extends SavableModel> ModelDataBinder<M> newBinder(Class<M> modelClass);
}
