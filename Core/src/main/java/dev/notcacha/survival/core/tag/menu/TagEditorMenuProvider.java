package dev.notcacha.survival.core.tag.menu;

import dev.notcacha.survival.api.util.Validate;
import dev.notcacha.survival.core.menu.gui.AbstractGUIMenuProvider;
import me.yushust.message.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.unnamed.gui.core.gui.type.GUIBuilder;

import javax.inject.Inject;


public class TagEditorMenuProvider extends AbstractGUIMenuProvider {

    private final static String PATH = "inventory.tag_editor.%s";

    private final MessageHandler messageHandler;

    @Inject
    public TagEditorMenuProvider(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public GUIBuilder getGui() {
        Player player = Bukkit.getPlayer((String) getIfPresent("player_id"));

        Validate.nonNull(player, "The player from tag editor menu is null.");

        GUIBuilder builder = GUIBuilder.builder(
                messageHandler.get(
                        player,
                        String.format(
                                PATH,
                                "title"
                        )
                ),
                Integer.parseInt(messageHandler.get(
                        player,
                        String.format(
                                PATH,
                                "size"
                        )
                ))
        );



        return builder;
    }

}
