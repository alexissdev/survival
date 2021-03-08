package dev.notcacha.survival.core.matcher;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.concurrent.AsyncResponse;
import dev.notcacha.survival.api.concurrent.Response;
import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.core.concurrent.SimpleAsyncResponse;
import dev.notcacha.survival.core.concurrent.WrappedResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CoreModelMatcher<T extends SavableModel> implements ModelMatcher<T> {

    @Inject
    private ObjectCache<T> modelObjectCache;
    @Inject
    private ListeningExecutorService executorService;

    @Override
    public Optional<T> findModelByIdSync(String modelId) {
        return modelObjectCache.findIfPresent(modelId);
    }

    @Override
    public AsyncResponse<T> findModelById(String modelId) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            Optional<T> modelResponse = findModelByIdSync(modelId);

            return modelResponse.map(model -> new WrappedResponse(Response.Status.SUCCESS, model)).orElseGet(() -> new WrappedResponse(Response.Status.ERROR, null));
        }));
    }
}
