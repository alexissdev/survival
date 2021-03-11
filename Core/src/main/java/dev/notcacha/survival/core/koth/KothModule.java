package dev.notcacha.survival.core.koth;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.koth.Koth;
import dev.notcacha.survival.core.binder.CoreModelDataBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class KothModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelDataBinder<Koth> modelDataBinder = new CoreModelDataBinder<>(
                binder(),
                Koth.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Koth.class),
                        "/tags/"
                )
        );

        modelDataBinder.bindStorage()
                .bindCache(ModelCache.Type.DEFAULT)
                .bindMatcher()
                .bindProcessors().bindAll();
    }
}
