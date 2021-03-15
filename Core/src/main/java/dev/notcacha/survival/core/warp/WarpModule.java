package dev.notcacha.survival.core.warp;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.warp.Warp;
import dev.notcacha.survival.core.binder.CoreModelBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class WarpModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Warp> modelBinder = new CoreModelBinder<>(
                binder(),
                Warp.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Warp.class),
                        "/warps/"
                )
        );

        modelBinder.bindStorage()
                .bindCache()
                .bindTemporary()
                .back()
                .bindMatcher()
                .bindProcessors()
                .bindAll();
    }
}
