package dev.notcacha.survival.api.matcher;

import dev.notcacha.survival.api.concurrent.AsyncResponse;
import dev.notcacha.survival.api.model.SavableModel;

import java.util.Optional;

public interface ModelMatcher<T extends SavableModel> {

    /**
     * Find the model {@link T} in cache and in any case that it is not present, proceed to look for it in the storages.
     *
     * @return The model has been find.
     */

    Optional<T> findModelByIdSync(String modelId);

    /**
     * @see ModelMatcher#findModelByIdSync(String)
     * <p>
     * It does the same as the above method only asynchronously
     */

    AsyncResponse<T> findModelById(String modelId);
}
