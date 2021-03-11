package dev.notcacha.survival.core.menu;

import dev.notcacha.survival.api.menu.MenuProvider;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMenuProvider implements MenuProvider {

    private final Map<String, Object> objectMap = new HashMap<>();

    @Override
    public @Nullable Map<String, Object> getObjects() {
        return objectMap;
    }

    /**
     * Add the object to cache
     *
     * @param identifier from object.
     * @param <T>        reference to the object to be added
     * @param object     has been added.
     * @return This class instance.
     */

    public <T> AbstractMenuProvider addObject(String identifier, T object) {

        objectMap.put(identifier, object);

        return this;
    }

    /**
     * @return an object {@link T} if it is present in the cache
     */

    public <T> T getIfPresent(String identifier) {
        return (T) objectMap.get(identifier);
    }

}
