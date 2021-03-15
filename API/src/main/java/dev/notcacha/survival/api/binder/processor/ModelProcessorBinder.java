package dev.notcacha.survival.api.binder.processor;

import dev.notcacha.survival.api.binder.back.ModelBinderBackLayout;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.Processor;

public interface ModelProcessorBinder<T extends SavableModel> extends ModelBinderBackLayout<T> {

    /**
     * Bind the ModelDeleteProcessor from {@link T} model.
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindDelete();

    /**
     * Bind the CachedModelProcessor from {@link T} model.
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindCached();

    /**
     * Bind an {@link Processor} external to the program.
     *
     * @param processorClass That will be blessed
     * @param <P>            Refers to the {@link Processor}
     * @return This class instance.
     */

    <P extends Processor, I extends P> ModelProcessorBinder<T> bindCustom(Class<P> processorClass, Class<I> implementProcessorClass);

    /**
     * Bind all processes that are available for the model {@link T}
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindAll();
}
