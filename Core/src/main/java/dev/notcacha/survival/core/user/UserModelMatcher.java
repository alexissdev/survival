package dev.notcacha.survival.core.user;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.user.User;
import dev.notcacha.survival.api.user.creator.UserCreator;
import dev.notcacha.survival.core.matcher.CoreModelMatcher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserModelMatcher extends CoreModelMatcher<User> {

    @Inject
    public UserModelMatcher(ModelCache<User> modelModelCache, ListeningExecutorService executorService) {
        super(modelModelCache, executorService);
    }

    @Override
    public Optional<User> findModelByIdSync(String modelId) {
        User user = super.findModelByIdSync(modelId).orElse(null);

        if (user == null) {
            user = UserCreator.create(UserCreator.UserProperties.ofOfflinePlayer(modelId));
        }

        return Optional.of(user);
    }
}
