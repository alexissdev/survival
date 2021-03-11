package dev.notcacha.survival.api.processor;

import dev.notcacha.survival.api.model.SavableModel;

public interface ModelProcessor<T extends SavableModel> extends Processor {

    /**
     * Start the process from model.
     *
     * @param model from process.
     */

    void process(T model);

}
