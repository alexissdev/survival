package dev.notcacha.survival.core.matcher;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.cache.ModelCache;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Singleton
public class CoreModelMatcher<T extends SavableModel> implements ModelMatcher<T> {

    private final ModelCache<T> modelModelCache;
    private final ListeningExecutorService executorService;

    @Inject
    public CoreModelMatcher(ModelCache<T> modelModelCache, ListeningExecutorService executorService) {
        this.modelModelCache = modelModelCache;
        this.executorService = executorService;
    }

    @Override
    public Optional<T> findModelByIdSync(String modelId) {
        return modelModelCache.findIfPresent(modelId);
    }

    @Override
    public CompletableFuture<Optional<T>> findModelById(String modelId) {
        return CompletableFuture.supplyAsync(() -> findModelByIdSync(modelId), executorService);
    }
}
