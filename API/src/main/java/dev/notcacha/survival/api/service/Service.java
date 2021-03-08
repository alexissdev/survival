package dev.notcacha.survival.api.service;

public interface Service {

    /**
     * Start service.
     */

    void start();

    /**
     * Stop service.
     */

    default void stop() {
        //TODO: This method must be abstracted if necessary
    }
}
