package dev.notcacha.survival.api.event.user;

import dev.notcacha.survival.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class UserLeaveEvent extends UserEvent {

    private final static HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;

    public UserLeaveEvent(User user, Player player) {
        super(user);
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * @return The player holder from this event.
     */

    public Player getPlayer() {
        return player;
    }
}
