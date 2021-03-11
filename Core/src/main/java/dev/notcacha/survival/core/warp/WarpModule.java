package dev.notcacha.survival.core.warp;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.warp.Warp;
import dev.notcacha.survival.core.binder.CoreModelDataBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class WarpModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelDataBinder<Warp> modelDataBinder = new CoreModelDataBinder<>(
                binder(),
                Warp.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Warp.class),
                        "/warps/"
                )
        );

        modelDataBinder.bindStorage()
                .bindCache(ModelCache.Type.TEMPORARY)
                .bindMatcher()
                .bindProcessors()
                .bindAll();
    }
}
