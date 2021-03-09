package dev.notcacha.survival.core.binder.processor;

import dev.notcacha.survival.api.binder.processor.ModelProcessorBinder;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.processor.ModelDeleteProcessor;
import dev.notcacha.survival.api.processor.Processor;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.processor.CoreModelDeleteProcessor;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelProcessorBinder<T extends SavableModel> implements ModelProcessorBinder<T> {

    private final static String ERROR_MESSAGE = "The %s from Model Processor Binder is null.";

    private final Binder binder;
    private final Class<T> modelClass;

    public CoreModelProcessorBinder(Binder binder, Class<T> modelClass) {
        this.binder = Validate.nonNull(binder, String.format(ERROR_MESSAGE, "binder"));
        this.modelClass = Validate.nonNull(modelClass, String.format(ERROR_MESSAGE, "model class"));
    }

    @Override
    public ModelProcessorBinder<T> bindDelete() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelDeleteProcessor.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(CoreModelDeleteProcessor.class, modelClass)
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
    public <Model extends SavableModel> ModelProcessorBinder<Model> newBinder(Class<Model> modelClass) {
        Validate.nonNull(modelClass, String.format(ERROR_MESSAGE, "new model class"));

        return new CoreModelProcessorBinder<>(binder, modelClass);
    }

    @Override
    public ModelProcessorBinder<T> bindAll() {
        bindDelete();

        return this;
    }
}
