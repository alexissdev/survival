package dev.notcacha.survival.core.kit;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.kit.applier.KitApplier;
import dev.notcacha.survival.core.binder.CoreModelDataBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class KitModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelDataBinder<Kit> modelDataBinder = new CoreModelDataBinder<>(
                binder(),
                Kit.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Kit.class),
                        "/kits/"
                )
        );

        modelDataBinder.bindStorage()
                .bindCache(ModelCache.Type.TEMPORARY)
                .bindMatcher()
                .bindProcessors()
                .bindAll();

        bind(KitApplier.class).to(CoreKitApplier.class);
    }
}
