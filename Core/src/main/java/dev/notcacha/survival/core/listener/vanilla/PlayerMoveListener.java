package dev.notcacha.survival.core.listener.vanilla;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.survival.api.cache.ObjectCache;
import dev.notcacha.survival.api.event.koth.KothEnterEvent;
import dev.notcacha.survival.api.event.koth.KothLeaveEvent;
import dev.notcacha.survival.api.koth.Koth;
import dev.notcacha.survival.api.util.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.inject.Inject;
import java.util.Optional;

public class PlayerMoveListener implements Listener {

    @Inject
    private ListeningExecutorService executorService;
    @Inject
    private ObjectCache<Koth> kothObjectCache;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        executorService.submit(() -> {

            for (Koth koth : kothObjectCache.getAllPresent()) {
                Optional<Cuboid.Repository> cuboidRepositoryOptional = koth.getCuboidRepository();
                if (!cuboidRepositoryOptional.isPresent()) {
                    continue;
                }

                Cuboid.Repository cuboidRepository = cuboidRepositoryOptional.get();

                if (!cuboidRepository.toCuboid().isIn(player)) {
                    if (koth.getSettings().getPlayersIdWhoAreInside().contains(player.getUniqueId().toString())) {

                        Bukkit.getServer().getPluginManager().callEvent(
                                new KothLeaveEvent(
                                        koth,
                                        player
                                )
                        );

                        return;
                    }
                    continue;
                }


                koth.getSettings().getPlayersIdWhoAreInside().add(player.getUniqueId().toString());

                Bukkit.getServer().getPluginManager().callEvent(
                        new KothEnterEvent(
                                koth,
                                player
                        )
                );
            }

        });
    }
}
