package dev.notcacha.survival.api.processor;

import dev.notcacha.survival.api.model.SavableModel;

public interface ModelDeleteProcessor<T extends SavableModel> extends Processor {

    /**
     * Start the delete processor.
     * <p>
     * Delete {@link T} model from storage and cache.
     */

    void process(T model);

}
