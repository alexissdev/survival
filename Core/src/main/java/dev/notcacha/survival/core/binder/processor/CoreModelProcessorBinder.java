package dev.notcacha.survival.core.binder.processor;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.processor.ModelProcessorBinder;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.api.processor.Processor;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.processor.CachedModelProcessor;
import dev.notcacha.survival.core.processor.DeleteModelProcessor;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelProcessorBinder<T extends SavableModel> implements ModelProcessorBinder<T> {

    private final static String ERROR_MESSAGE = "The %s from Model Processor Binder is null.";

    private final Binder binder;
    private final Class<T> modelClass;

    private final ModelBinder<T> modelBinderBack;

    public CoreModelProcessorBinder(Binder binder, Class<T> modelClass, ModelBinder<T> modelBinderBack) {
        this.binder = Validate.nonNull(binder, String.format(ERROR_MESSAGE, "binder"));
        this.modelClass = Validate.nonNull(modelClass, String.format(ERROR_MESSAGE, "model class"));
        this.modelBinderBack = modelBinderBack;
    }

    @Override
    public ModelProcessorBinder<T> bindDelete() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelProcessor.class, modelClass)
        ).named(
                "delete"
        ).to(
                TypeReferenceUtil.getParameterized(DeleteModelProcessor.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelProcessorBinder<T> bindCached() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelProcessor.class, modelClass)
        ).named(
                "cached"
        ).to(
                TypeReferenceUtil.getParameterized(CachedModelProcessor.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public <P extends Processor, I extends P> ModelProcessorBinder<T> bindCustom(Class<P> processorClass, Class<I> implementProcessorClass) {
        Validate.nonNull(processorClass, String.format(ERROR_MESSAGE, "custom processor"));
        Validate.nonNull(implementProcessorClass, String.format(ERROR_MESSAGE, "implement custom processor"));

        binder.bind(
                TypeReferenceUtil.getParameterized(processorClass, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(implementProcessorClass, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelProcessorBinder<T> bindAll() {
        bindDelete();
        bindCached();

        return this;
    }

    @Override
    public ModelBinder<T> back() {
        return modelBinderBack;
    }
}
