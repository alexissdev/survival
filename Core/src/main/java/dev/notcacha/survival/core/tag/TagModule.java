package dev.notcacha.survival.core.tag;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.tag.applier.TagApplier;
import dev.notcacha.survival.core.binder.CoreModelDataBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;

public class TagModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelDataBinder<Tag> modelDataBinder = new CoreModelDataBinder<>(
                binder(),
                Tag.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Tag.class),
                        "/tags/"
                )
        );

        modelDataBinder.bindStorage()
                .bindMatcher()
                .bindCache(ObjectCache.Type.DEFAULT)
                .bindProcessors().bindAll();

        bind(TagApplier.class).to(CoreTagApplier.class);
    }
}
