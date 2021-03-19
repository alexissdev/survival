package dev.notcacha.survival.core.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.storage.ModelStorage;
import dev.notcacha.survival.core.file.JsonFile;
import dev.notcacha.survival.core.file.creator.JsonFileCreator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Singleton
public class JsonModelStorage<T extends SavableModel> implements ModelStorage<T> {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private JsonFileCreator jsonFileCreator;

    private final String folderName;
    private final Class<T> modelClass;

    @Inject
    public JsonModelStorage(ModelBinderData<T> modelBinderData) {
        this.modelClass = modelBinderData.getType().getRawType();
        this.folderName = (String) modelBinderData.getAlternativeData().get("folder");
    }

    @Override
    public Optional<T> findOneSync(String id) {
        JsonFile modelFile = jsonFileCreator.create(folderName, String.format(JsonFileCreator.FILE_NAME_FORMAT, id));

        return Optional.ofNullable(
                modelFile.toModel(
                        modelClass
                )
        );
    }

    @Override
    public CompletableFuture<Optional<T>> findOne(String id) {
        return CompletableFuture.supplyAsync(() -> findOneSync(id), executorService);
    }

    @Override
    public Set<T> getAllSync() {
        return null;
    }

    @Override
    public CompletableFuture<Set<T>> getAll() {
        return CompletableFuture.supplyAsync(this::getAllSync, executorService);
    }

    @Override
    public CompletableFuture<Void> save(T object) {
        return CompletableFuture.supplyAsync(() -> {

            JsonFile modelFile = jsonFileCreator.create(folderName, String.format(JsonFileCreator.FILE_NAME_FORMAT, object.getId()));

            modelFile.write(object);
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> delete(String id) {
        return CompletableFuture.supplyAsync(() -> {

            JsonFile modelFile = jsonFileCreator.create(folderName, String.format(JsonFileCreator.FILE_NAME_FORMAT, id));

            if (modelFile.exists()) {
                modelFile.delete();
            }

            return null;
        }, executorService);
    }
}
