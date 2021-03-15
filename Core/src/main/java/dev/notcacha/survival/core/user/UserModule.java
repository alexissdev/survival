package dev.notcacha.survival.core.user;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.core.binder.CoreModelBinder;
import dev.notcacha.survival.core.util.TypeReferenceUtil;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<User> modelBinder = new CoreModelBinder<>(
                binder(),
                User.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(User.class),
                        "/users/"
                )
        );

        modelBinder.bindStorage()
                .bindCache()
                .bindTemporary()
                .back()
                .bindProcessors()
                .bindAll();

        bind(TypeReference.of(ModelMatcher.class)).to(UserModelMatcher.class).singleton();
    }
}
