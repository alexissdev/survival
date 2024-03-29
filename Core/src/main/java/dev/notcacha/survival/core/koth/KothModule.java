package dev.notcacha.survival.core.koth;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.koth.Koth;
import dev.notcacha.survival.core.binder.CoreModelBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class KothModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Koth> modelBinder = new CoreModelBinder<>(
                binder(),
                Koth.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Koth.class),
                        "/tags/"
                )
        );

        modelBinder.bindStorage()
                .bindCache()
                .bindDefault()
                .back()
                .bindMatcher()
                .bindProcessors().bindAll();
    }
}
