package dev.notcacha.survival.core.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dev.notcacha.survival.api.concurrent.AsyncResponse;
import dev.notcacha.survival.api.concurrent.Callback;
import dev.notcacha.survival.api.concurrent.Response;

public class SimpleAsyncResponse<T> implements AsyncResponse<T> {

    private final ListenableFuture<Response<T>> responseFuture;

    public SimpleAsyncResponse(ListenableFuture<Response<T>> responseFuture) {
        this.responseFuture = responseFuture;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void callback(Callback<Response<T>> callback) {
        Futures.addCallback(this.responseFuture, this.wrapCallback(callback));
    }

    private FutureCallback<Response<T>> wrapCallback(Callback<Response<T>> callback) {
        return new FutureCallback<Response<T>>() {

            @Override
            public void onSuccess(Response<T> response) {
                callback.call(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.handleException(throwable);
            }
        };
    }

}