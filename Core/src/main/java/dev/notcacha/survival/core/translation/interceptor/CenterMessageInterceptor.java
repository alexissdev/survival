package dev.notcacha.survival.core.translation.interceptor;

import me.yushust.message.format.MessageInterceptor;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class CenterMessageInterceptor implements MessageInterceptor {

    private static final String CENTER_KEYWORD = "%%center%%";

    private String interceptOneLine(String text) {
        if (!text.startsWith(CENTER_KEYWORD)) {
            return text;
        }
        text = text.substring(CENTER_KEYWORD.length());
        return ChatCenter.getCenteredMessage(text);
    }

    @Override
    public @NotNull String intercept(String text) {
        String[] args = text.split("\n");

        for (int i = 0; i < args.length; i++) {
            args[i] = interceptOneLine(args[i]);
        }

        return String.join("\n", args);
    }

    private static class ChatCenter {

        private final static int CENTER_PX = 154;

        private ChatCenter() {
            throw new UnsupportedOperationException("This class couldn't be instantiated");
        }

        public static String getCenteredMessage(String message) {
            message = ChatColor.translateAlternateColorCodes('&', message);

            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for (char c : message.toCharArray()) {
                if (c == '§') {
                    previousCode = true;
                } else if (previousCode) {
                    previousCode = false;
                    isBold = c == 'l' || c == 'L';
                } else {
                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                    messagePxSize += isBold ? dFI.getBoldLength(c) : dFI.getLength();
                    messagePxSize++;
                }
            }

            int halvedMessageSize = messagePxSize / 2;
            int toCompensate = CENTER_PX - halvedMessageSize;
            int spaceLength = 4;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while (compensated < toCompensate) {
                sb.append(" ");
                compensated += spaceLength;
            }
            return sb.toString() + message;
        }

        private static enum DefaultFontInfo {

            SIX_SYMBOLS(6, '@'),
            FIVE_SYMBOLS(5, 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'J', 'j', 'K',
                    'L', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 'U', 'u',
                    'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', 'G', 'g', 'H', 'h', '1', '2', '3', '4',
                    '5', '6', '7', '8', '9', '0', '#', '$', '%', '^', '&', '*', '-', '_', '+', '=', '?', '/',
                    '\\', '~'),
            FOUR_SYMBOLS(4, 'f', 'k', 't', '(', ')', '{', '}', '<', '>'),
            THREE_SYMBOLS(3, '[', ']', '"', ' ', 'I'),
            TWO_SYMBOLS(2, '`'),
            ONE_SYMBOLS(1, ':', ';', '\'', '|', '.', ',', 'i', 'l', '!'),
            DEFAULT(4);

            private final char[] characters;
            private final int length;

            DefaultFontInfo(int length, char... characters) {
                this.characters = characters;
                this.length = length;
            }

            public char[] getCharacters() {
                return this.characters;
            }

            public int getLength() {
                return this.length;
            }

            public int getBoldLength(char c) {
                if (c == ' ') {
                    return this.getLength();
                }
                return this.length + 1;
            }

            public static DefaultFontInfo getDefaultFontInfo(char c) {
                for (DefaultFontInfo fontInfo : DefaultFontInfo.values()) {
                    for (char character : fontInfo.getCharacters()) {
                        if (character == c) {
                            return fontInfo;
                        }
                    }
                }
                return DefaultFontInfo.DEFAULT;
            }
        }
    }

}
