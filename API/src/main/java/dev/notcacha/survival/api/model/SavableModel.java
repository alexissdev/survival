package dev.notcacha.survival.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface SavableModel {

    /**
     * @return The identifier from model.
     */

    @JsonProperty("_id")
    String getId();
}
