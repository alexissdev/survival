package dev.notcacha.survival.core.kit;

import dev.notcacha.survival.api.kit.Kit;

public class CoreKit implements Kit {

    private final String id;
    private final Configuration configuration;
    private final Contents contents;

    public CoreKit(String id, Configuration configuration, Contents contents) {
        this.id = id;
        this.configuration = configuration;
        this.contents = contents;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public Contents getContents() {
        return contents;
    }

    @Override
    public String getId() {
        return id;
    }
}
