package dev.notcacha.survival.core.tag;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.tag.applier.TagApplier;
import dev.notcacha.survival.core.binder.CoreModelBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class TagModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Tag> modelBinder = new CoreModelBinder<>(
                binder(),
                Tag.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Tag.class),
                        "/tags/"
                )
        );

        modelBinder.bindStorage()
                .bindMatcher()
                .bindCache()
                .bindDefault()
                .back()
                .bindProcessors().bindAll();

        bind(TagApplier.class).to(CoreTagApplier.class);
    }
}
