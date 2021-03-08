package dev.notcacha.survival.api.event.koth;

import dev.notcacha.survival.api.koth.Koth;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class KothEnterEvent extends KothEvent {

    private final Player player;
    private final static HandlerList HANDLER_LIST = new HandlerList();

    public KothEnterEvent(Koth koth, Player player) {
        super(koth);
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * @return The player has been enter to koth.
     */

    public Player getPlayer() {
        return player;
    }
}

