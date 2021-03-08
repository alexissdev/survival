package dev.notcacha.survival.core.binder;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.storage.ModelStorage;
import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.cache.CaffeineObjectCache;
import dev.notcacha.survival.core.cache.MapObjectCache;
import dev.notcacha.survival.core.matcher.CoreModelMatcher;
import dev.notcacha.survival.core.storage.JsonModelStorage;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelDataBinder<T extends SavableModel> implements ModelDataBinder<T> {

    private final Binder binder;
    private final Class<T> modelClass;

    public CoreModelDataBinder(Binder binder, Class<T> modelClass) {
        this(binder, modelClass, ModelBinderData.create(TypeReferenceUtil.getTypeOf(modelClass)));
    }

    public CoreModelDataBinder(Binder binder, Class<T> modelClass, ModelBinderData<T> modelData) {
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
    public ModelDataBinder<T> bindStorage() {
        binder.bind(
                TypeReferenceUtil.getParameterized(ModelStorage.class, modelClass
                )).to(
                TypeReferenceUtil.getParameterized(JsonModelStorage.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelDataBinder<T> bindCache(ObjectCache.Type type) {

        switch (type) {

            case TEMPORARY: {
                binder.bind(
                        TypeReferenceUtil.getParameterized(ObjectCache.class, modelClass
                        )).to(
                        TypeReferenceUtil.getParameterized(CaffeineObjectCache.class, modelClass)
                ).singleton();
            }

            case DEFAULT: {
                binder.bind(
                        TypeReferenceUtil.getParameterized(ObjectCache.class, modelClass
                        )).to(
                        TypeReferenceUtil.getParameterized(MapObjectCache.class, modelClass)
                ).singleton();
            }

        }

        return this;
    }

    @Override
    public ModelDataBinder<T> bindMatcher() {
        binder.bind(
                TypeReferenceUtil.getParameterized(ModelMatcher.class, modelClass
                )).to(
                TypeReferenceUtil.getParameterized(CoreModelMatcher.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public <M extends SavableModel> ModelDataBinder<M> newBinder(Class<M> modelClass) {
        return new CoreModelDataBinder<>(binder, modelClass);
    }

}
