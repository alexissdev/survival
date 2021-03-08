package dev.notcacha.survival.api.concurrent;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * This class will wrap an async response with a success status or an exception
 * @param <T> Interface/Class that will be wrapped
 */

public interface Response<T> {

    enum Status {
        SUCCESS, ERROR
    }

    /**
     * @return Async block status
     */

    Status getState();

    /**
     * @return Response block status
     */

    Optional<T> getResponse();

    /**
     * @return {@code true} if status is SUCCESS, otherwise {@code false}
     */

    default boolean isSuccessful() {
        return getState() == Status.SUCCESS;
    }

    /**
     * If response was successful, invoke the specified consumer with the value,
     * otherwise do nothing.
     * @param consumer block to be executed if a value is present
     * @throws NullPointerException if value is present and {@code consumer} is null
     */

    void ifSuccessful(Consumer<? super T> consumer);

}