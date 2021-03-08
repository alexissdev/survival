package dev.notcacha.survival.api.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    private final static String LOCATION_STRING_FORMAT = "%s;%s;%s;%s;%s;%s";

    public static Location byString(String location) {
        Validate.nonNull(location, "The string location is null.");

        String[] locationPart = location.split(";");

        return new Location(
                Bukkit.getWorld(locationPart[0]),
                Double.parseDouble(locationPart[1]),
                Double.parseDouble(locationPart[2]),
                Double.parseDouble(locationPart[3]),
                Float.parseFloat(locationPart[4]),
                Float.parseFloat(locationPart[5])
        );
    }

    public static String toString(Location location) {
        Validate.nonNull(location, "The location has been transform to string is null.");

        return String.format(
                LOCATION_STRING_FORMAT,
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getYaw()
        );
    }
}
