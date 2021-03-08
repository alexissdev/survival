package dev.notcacha.survival.api.koth.creator;

import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.koth.Koth;
import dev.notcacha.survival.api.util.Cuboid;

import java.time.LocalDateTime;
import java.util.*;

public interface KothCreator {

    /**
     * @return New instance from {@link Koth}
     * */

    static Koth create(String kothId) {
        return new Koth() {

            private final LocalDateTime localDateTime = LocalDateTime.now();
            private Cuboid.Repository cuboidRepository = null;

            @Override
            public String getId() {
                return kothId;
            }

            @Override
            public LocalDateTime getCreationDate() {
                return localDateTime;
            }

            @Override
            public Optional<Cuboid.Repository> getCuboidRepository() {
                return Optional.ofNullable(
                        cuboidRepository
                );
            }

            @Override
            public void setCuboidRepository(Cuboid.Repository cuboidRepository) {
                this.cuboidRepository = cuboidRepository;
            }

            @Override
            public Settings getSettings() {
                return new Settings() {

                    private boolean started = false;
                    private String capturingId = null;
                    private final Set<String> playersIdInWhoAreInsideSet = new HashSet<>();

                    @Override
                    public boolean isStarted() {
                        return started;
                    }

                    @Override
                    public void setStarted(boolean value) {
                        started = value;
                    }

                    @Override
                    public Optional<String> getCapturingId() {
                        return Optional.ofNullable(
                                capturingId
                        );
                    }

                    @Override
                    public void setCapturingId(String capturingId) {
                        this.capturingId = capturingId;
                    }

                    @Override
                    public Set<String> getPlayersIdWhoAreInside() {
                        return playersIdInWhoAreInsideSet;
                    }
                };
            }

            @Override
            public Loot getLoot() {
                return new Loot() {

                    private final Map<Integer, SerializableItem> itemMap = new HashMap<>();

                    @Override
                    public Map<Integer, SerializableItem> getItems() {
                        return itemMap;
                    }
                };
            }

        };
    }

}
