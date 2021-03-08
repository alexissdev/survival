package dev.notcacha.survival.core.loader;

import dev.notcacha.survival.api.service.Service;
import dev.notcacha.survival.core.listener.koth.KothEnterListener;
import dev.notcacha.survival.core.listener.koth.KothLeaveListener;
import dev.notcacha.survival.core.listener.user.UserJoinListener;
import dev.notcacha.survival.core.listener.user.UserLeaveListener;
import dev.notcacha.survival.core.listener.vanilla.PlayerJoinListener;
import dev.notcacha.survival.core.listener.vanilla.PlayerMoveListener;
import dev.notcacha.survival.core.listener.vanilla.PlayerQuitListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventLoaderService implements Service {

    @Inject
    private Plugin plugin;

    @Inject
    private EventProvider eventProvider;

    @Override
    public void start() {
        plugin.getLogger().info("[Event-Loader] The event loader service has been start.");

        register(eventProvider.get());
    }

     @Override
     public void stop() {
        plugin.getLogger().info("[Event-Loader] The event loader service has been stopped.");
     }

    private void register(Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Singleton
    private static class EventProvider  {

        @Inject
        private PlayerJoinListener playerJoinListener;
        @Inject
        private PlayerQuitListener playerQuitListener;
        @Inject
        private PlayerMoveListener playerMoveListener;

        @Inject
        private UserJoinListener userJoinListener;
        @Inject
        private UserLeaveListener userLeaveListener;
        @Inject

        private KothEnterListener kothEnterListener;
        @Inject
        private KothLeaveListener kothLeaveListener;

        public Listener[] get() {
            return new Listener[]{
                    playerJoinListener,
                    playerQuitListener,
                    playerMoveListener,
                    userJoinListener,
                    userLeaveListener,
                    kothEnterListener,
                    kothLeaveListener
            };
        }

    }
}
