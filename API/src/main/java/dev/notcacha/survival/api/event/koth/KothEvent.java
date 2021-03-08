package dev.notcacha.survival.api.event.koth;

import dev.notcacha.survival.api.koth.Koth;
import org.bukkit.event.Event;

public abstract class KothEvent extends Event {

    private final Koth koth;

    protected KothEvent(Koth koth) {
        this.koth = koth;
    }

    /**
     * @return The koth holder from this event.
     */

    public Koth getKoth() {
        return koth;
    }
}
