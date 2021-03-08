package dev.notcacha.survival.api.util;

import org.bukkit.ChatColor;

import java.util.List;

public interface Colorize {

    static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    static List<String> colorize(List<String> list) {
        list.replaceAll(Colorize::colorize);

        return list;
    }
}
