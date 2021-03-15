package dev.notcacha.survival.core.file.creator;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.notcacha.survival.core.file.JsonFile;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;

@Singleton
public class JsonFileCreator {

    public final static String FILE_NAME_FORMAT = "%s.json";

    private final ObjectMapper objectMapper;
    private final Plugin plugin;

    @Inject
    public JsonFileCreator(ObjectMapper objectMapper, Plugin plugin) {
        this.objectMapper = objectMapper;
        this.plugin = plugin;
    }

    public JsonFile create(String folderName, String fileName) {
        return new JsonFile(
                plugin,
                objectMapper,
                new File(
                        plugin.getDataFolder(),
                        folderName
                ),
                fileName
        );
    }
}
