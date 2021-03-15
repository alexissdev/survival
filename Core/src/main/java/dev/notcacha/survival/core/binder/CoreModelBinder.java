package dev.notcacha.survival.core.binder;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.cache.ModelCacheBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.binder.processor.ModelProcessorBinder;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.storage.ModelStorage;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.binder.cache.CoreModelCacheBinder;
import dev.notcacha.survival.core.binder.processor.CoreModelProcessorBinder;
import dev.notcacha.survival.core.matcher.CoreModelMatcher;
import dev.notcacha.survival.core.storage.JsonModelStorage;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelBinder<T extends SavableModel> implements ModelBinder<T> {

    private final Binder binder;
    private final Class<T> modelClass;

    public CoreModelBinder(Binder binder, Class<T> modelClass) {
        this(binder, modelClass, ModelBinderData.create(TypeReferenceUtil.getTypeOf(modelClass)));
    }

    public CoreModelBinder(Binder binder, Class<T> modelClass, ModelBinderData<T> modelData) {
        this.binder = Validate.nonNull(binder, "The binder of ModelDataBinder is null.");
        this.modelClass = Validate.nonNull(modelClass, "The model class from ModelDataBinder is null.");

        Validate.nonNull(modelData, "The model data of ModelDataBinder is null.");

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelBinderData.class, modelClass)
        ).toInstance(
                modelData
        );
    }

    @Override
    public ModelBinder<T> bindStorage() {
        binder.bind(
                TypeReferenceUtil.getParameterized(ModelStorage.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(JsonModelStorage.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelBinder<T> bindMatcher() {
        binder.bind(
                TypeReferenceUtil.getParameterized(ModelMatcher.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(CoreModelMatcher.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelCacheBinder<T> bindCache() {
        return new CoreModelCacheBinder<>(binder, modelClass, this);
    }

    @Override
    public ModelProcessorBinder<T> bindProcessors() {
        return new CoreModelProcessorBinder<>(binder, modelClass, this);
    }

    @Override
    public <M extends SavableModel> ModelBinder<M> newBinder(Class<M> modelClass) {
        Validate.nonNull(modelClass, "The new model class from new binder is null.");

        return new CoreModelBinder<>(binder, modelClass);
    }

}
