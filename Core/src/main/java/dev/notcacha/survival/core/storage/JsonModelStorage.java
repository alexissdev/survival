package dev.notcacha.survival.core.storage;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.binder.data.ModelBinderData;
import dev.notcacha.survival.api.concurrent.AsyncResponse;
import dev.notcacha.survival.api.concurrent.Response;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.storage.ModelStorage;
import dev.notcacha.survival.core.concurrent.SimpleAsyncResponse;
import dev.notcacha.survival.core.concurrent.WrappedResponse;
import dev.notcacha.survival.core.file.JsonFile;
import dev.notcacha.survival.core.file.creator.JsonFileCreator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

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
    public AsyncResponse<T> findOne(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            Optional<T> response = findOneSync(id);

            return response.map(model -> new WrappedResponse(Response.Status.SUCCESS, model)).orElseGet(() -> new WrappedResponse(Response.Status.ERROR, null));
        }));
    }

    @Override
    public Set<T> getAllSync() {
        return null;
    }

    @Override
    public AsyncResponse<Set<T>> getAll() {
        return new SimpleAsyncResponse<>(executorService.submit(() -> new WrappedResponse<>(Response.Status.SUCCESS, getAllSync())));
    }

    @Override
    public AsyncResponse<Void> save(T object) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            JsonFile modelFile = jsonFileCreator.create(folderName, String.format(JsonFileCreator.FILE_NAME_FORMAT, object.getId()));

            modelFile.write(object);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> delete(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            JsonFile modelFile = jsonFileCreator.create(folderName, String.format(JsonFileCreator.FILE_NAME_FORMAT, id));

            if (modelFile.exists()) {
                modelFile.delete();
            }

            return null;
        }));
    }
}
