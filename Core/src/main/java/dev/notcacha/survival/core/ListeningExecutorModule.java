package dev.notcacha.survival.core;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import me.yushust.inject.AbstractModule;

import java.util.concurrent.Executors;

public class ListeningExecutorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ListeningExecutorService.class).toInstance(MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4)));
    }
}
