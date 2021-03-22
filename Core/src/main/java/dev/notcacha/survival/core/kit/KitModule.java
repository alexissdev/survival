package dev.notcacha.survival.core.kit;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.kit.applier.KitApplier;
import dev.notcacha.survival.core.binder.CoreModelBinder;
import dev.notcacha.survival.core.kit.applier.CoreKitApplier;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class KitModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Kit> modelBinder = new CoreModelBinder<>(
                binder(),
                Kit.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Kit.class),
                        "/kits/"
                )
        );

        modelBinder.bindStorage()
                .bindCache()
                .bindTemporary()
                .back()
                .bindMatcher()
                .bindProcessors()
                .bindAll();

        bind(KitApplier.class).to(CoreKitApplier.class);
    }
}
