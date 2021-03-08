package dev.notcacha.survival.api.concurrent;

public interface AsyncResponse<T> {

    void callback(Callback<Response<T>> callback);

}
