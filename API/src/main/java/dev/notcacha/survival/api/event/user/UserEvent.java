package dev.notcacha.survival.api.event.user;

import dev.notcacha.survival.api.user.User;
import org.bukkit.event.Event;

public abstract class UserEvent extends Event {

    private final User user;

    protected UserEvent(User user) {
        this.user = user;
    }

    /**
     * @return The user holder from this event.
     */

    public User getUser() {
        return user;
    }
}
