package dev.notcacha.survival.api.cache;

import dev.notcacha.survival.api.model.SavableModel;

import java.util.Optional;
import java.util.Set;

public interface ModelCache<O extends SavableModel> {

    /**
     * @return An object {@link O} encapsulated in an {@link Optional} to prevent possible NPEs
     */

    Optional<O> findIfPresent(String objectId);

    /**
     * Add an object to cache.
     *
     * @param object has been added to cache.
     */

    void addObject(O object);

    /**
     * Remove an object to cache.
     *
     * @param objectId from object has been removed from cache.
     */

    void removeObject(String objectId);

    /**
     * @return If the object is cached.
     */

    boolean ifPresent(String objectId);

    /**
     * @return All objects that are present in the cache.
     */

    Set<O> getAllPresent();

}
