package dev.notcacha.survival.core.user;

import dev.notcacha.survival.api.user.User;

public class CoreUser implements User {

    private final String id;
    private final String username;
    private String language;
    private final StatisticHandler statisticHandler;
    private final BackpackHandler backpackHandler;
    private final TagCompound tagCompound;

    public CoreUser(String id, String username, String language, StatisticHandler statisticHandler, BackpackHandler backpackHandler, TagCompound tagCompound) {
        this.id = id;
        this.username = username;
        this.language = language;
        this.statisticHandler = statisticHandler;
        this.backpackHandler = backpackHandler;
        this.tagCompound = tagCompound;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public StatisticHandler getStatisticHandler() {
        return statisticHandler;
    }

    @Override
    public BackpackHandler getBackpackHandler() {
        return backpackHandler;
    }

    @Override
    public TagCompound getTagCompound() {
        return tagCompound;
    }
}
