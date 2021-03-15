package dev.notcacha.survival.core.binder.cache;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.cache.ModelCacheBinder;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.core.cache.CaffeineModelCache;
import dev.notcacha.survival.core.cache.MapModelCache;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelCacheBinder<T extends SavableModel> implements ModelCacheBinder<T> {

    private final Binder binder;
    private final Class<T> type;

    private final ModelBinder<T> modelBinderBack;

    public CoreModelCacheBinder(Binder binder, Class<T> type, ModelBinder<T> modelBinderBack) {
        this.binder = binder;
        this.type = type;
        this.modelBinderBack = modelBinderBack;
    }

    @Override
    public ModelCacheBinder<T> bindTemporary() {

        binder.bind(TypeReferenceUtil.getParameterized(ModelCache.class, type))
                .to(
                        TypeReferenceUtil.getParameterized(CaffeineModelCache.class, type)
        ).singleton();

        return this;
    }

    @Override
    public ModelCacheBinder<T> bindDefault() {

        binder.bind(TypeReferenceUtil.getParameterized(ModelCache.class, type))
                .to(
                        TypeReferenceUtil.getParameterized(MapModelCache.class, type)
                ).singleton();

        return this;
    }

    @Override
    public ModelBinder<T> back() {
        return modelBinderBack;
    }
}
