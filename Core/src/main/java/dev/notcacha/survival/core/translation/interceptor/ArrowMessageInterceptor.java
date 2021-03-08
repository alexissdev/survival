package dev.notcacha.survival.core.translation.interceptor;

import me.yushust.message.format.MessageInterceptor;
import org.jetbrains.annotations.NotNull;

public class ArrowMessageInterceptor implements MessageInterceptor {

    @Override
    public @NotNull String intercept(String text) {
        return text.replace(
                "%%arrow%%",
                "»"
        );
    }

}