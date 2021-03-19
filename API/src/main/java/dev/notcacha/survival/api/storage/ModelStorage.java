package dev.notcacha.survival.api.storage;

import dev.notcacha.survival.api.model.SavableModel;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ModelStorage<T extends SavableModel> {

    /**
     * Find an object in the database repository with their id
     *
     * @param id The id of the object to find
     * @return An optional sync response of the object
     */

    Optional<T> findOneSync(String id);

    /**
     * Find an object in the database repository with their id
     *
     * @param id The id of the object to find
     * @return An optional async response of the object
     */

    CompletableFuture<Optional<T>> findOne(String id);

    /**
     * Get all the objects stored in the database repository collection
     *
     * @return All the objects found at the repository collection
     */

    Set<T> getAllSync();

    /**
     * Get all the objects stored in the database repository collection
     *
     * @return An async response with all the objects found at the repository collection
     */

    CompletableFuture<Set<T>> getAll();

    /**
     * Save an object with the same type of the generic type and save in the database
     *
     * @param object The object to save
     * @return An async void response indicating that was saved
     */

    CompletableFuture<Void> save(T object);

    /**
     * Delete an object of the database
     *
     * @param id The id of the object to delete
     * @return An async void response indicating that was deleted
     */

    CompletableFuture<Void> delete(String id);

}
