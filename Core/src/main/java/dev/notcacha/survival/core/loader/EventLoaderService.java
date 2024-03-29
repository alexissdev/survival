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

    private final Plugin plugin;
    private final EventProvider eventProvider;

    @Inject
    public EventLoaderService(Plugin plugin, EventProvider eventProvider) {
        this.plugin = plugin;
        this.eventProvider = eventProvider;
    }

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

        private final PlayerJoinListener playerJoinListener;
        private final PlayerQuitListener playerQuitListener;
        private final PlayerMoveListener playerMoveListener;
        private final UserJoinListener userJoinListener;
        private final UserLeaveListener userLeaveListener;
        private final KothEnterListener kothEnterListener;
        private final KothLeaveListener kothLeaveListener;

        @Inject
        private EventProvider(PlayerJoinListener playerJoinListener, PlayerQuitListener playerQuitListener, PlayerMoveListener playerMoveListener, UserJoinListener userJoinListener, UserLeaveListener userLeaveListener, KothEnterListener kothEnterListener, KothLeaveListener kothLeaveListener) {
            this.playerJoinListener = playerJoinListener;
            this.playerQuitListener = playerQuitListener;
            this.playerMoveListener = playerMoveListener;
            this.userJoinListener = userJoinListener;
            this.userLeaveListener = userLeaveListener;
            this.kothEnterListener = kothEnterListener;
            this.kothLeaveListener = kothLeaveListener;
        }

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
