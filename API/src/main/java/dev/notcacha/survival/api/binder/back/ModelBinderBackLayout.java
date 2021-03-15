package dev.notcacha.survival.api.binder.back;

import dev.notcacha.survival.api.binder.ModelBinder;
import dev.notcacha.survival.api.model.SavableModel;

public interface ModelBinderBackLayout<T extends SavableModel> {

    /**
     * @return The back instance class {@link ModelBinder} from bind other properties.
     */

    ModelBinder<T> back();
}
