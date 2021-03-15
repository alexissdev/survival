package dev.notcacha.survival.core.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.notcacha.survival.api.model.SavableModel;
import dev.notcacha.survival.api.util.Validate;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class JsonFile {

    private final Plugin plugin;
    private final ObjectMapper objectMapper;

    private final File file;
    private final String fileName;

    public JsonFile(Plugin plugin, ObjectMapper objectMapper, File folder, String fileName) {
        this.plugin = plugin;
        this.objectMapper = objectMapper;
        this.fileName = (fileName.endsWith(".json") ? fileName : fileName + ".json");

        this.file = new File(folder, fileName);
        this.create();
    }

    private void create() {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    plugin.getLogger().info("The file " + fileName + " has been created.");
                }
            } catch (IOException exception) {
                plugin.getLogger().log(Level.SEVERE, "Error from create " + fileName + " file.", exception);
            }
        }
    }

    public <T> void write(T object) {
        try (FileWriter writer = new FileWriter(file)) {

            String value = ((object instanceof String) ? object.toString() : objectMapper.writeValueAsString(object));

            writer.write(value);

            writer.flush();
        } catch (IOException exception) {
            plugin.getLogger().log(Level.SEVERE, "Error from write " + fileName + " file.", exception);
        }
    }

    public String toJsonString() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line = "";

            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

        } catch (IOException exception) {
            plugin.getLogger().log(Level.SEVERE, "Error from reader " + fileName + " file.", exception);
        }

        return stringBuilder.toString();
    }

    public <T extends SavableModel> T toModel(Class<T> modelClass) {
        Validate.nonNull(modelClass, "The model class from " + fileName + " file creator is null.");

        try {
            return objectMapper.readValue(toJsonString(), modelClass);
        } catch (JsonProcessingException e) {
            plugin.getLogger().log(Level.SEVERE, "Error from parse " + fileName + " file to model.");

            return null;
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public boolean delete() {
        return file.delete();
    }
}
