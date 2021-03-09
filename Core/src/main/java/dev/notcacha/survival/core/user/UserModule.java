package dev.notcacha.survival.core.user;

import dev.notcacha.survival.api.binder.ModelDataBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.core.binder.CoreModelDataBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelDataBinder<User> modelDataBinder = new CoreModelDataBinder<>(
                binder(),
                User.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(User.class),
                        "/users/"
                )
        );

        modelDataBinder.bindStorage()
                .bindCache(ObjectCache.Type.TEMPORARY)
                .bindProcessors()
                .bindAll();

        bind(TypeReference.of(ModelMatcher.class)).to(UserModelMatcher.class).singleton();
    }
}
