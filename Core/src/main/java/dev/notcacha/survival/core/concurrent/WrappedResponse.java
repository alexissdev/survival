package dev.notcacha.survival.core.concurrent;

import dev.notcacha.survival.api.concurrent.Response;

import java.util.Optional;
import java.util.function.Consumer;

public class WrappedResponse<T> implements Response<T> {

    private final Status state;
    private final T response;

    public WrappedResponse(Status state, T response) {
        this.state = state;
        this.response = response;
    }

    @Override
    public Status getState() {
        return this.state;
    }

    @Override
    public Optional<T> getResponse() {
        return Optional.ofNullable(this.response);
    }

    @Override
    public void ifSuccessful(Consumer<? super T> consumer) {
        if (isSuccessful()) {
            consumer.accept(response);
        }
    }
}
