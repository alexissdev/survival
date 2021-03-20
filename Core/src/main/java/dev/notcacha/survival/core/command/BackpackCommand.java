package dev.notcacha.survival.core.command;

import dev.notcacha.survival.api.matcher.ModelMatcher;
import dev.notcacha.survival.api.user.User;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.logging.Level;

import static dev.notcacha.survival.core.util.InventoryUtil.*;

@Command(names = "backpack")
public class BackpackCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;

    @Inject
    private ModelMatcher<User> userModelMatcher;
    @Inject
    private Plugin plugin;

    @Command(names = "")
    public boolean backpack(@Sender Player player) {

        userModelMatcher.findModelById(player.getUniqueId().toString()).whenComplete((object, ignored) -> {

            if (!object.isPresent()) {
                plugin.getLogger().log(Level.SEVERE, "The user from id " + player.getUniqueId() + " not exists, check the storages.");
                return;
            }

            User user = object.get();

            User.BackpackHandler backpackHandler = user.getBackpackHandler();

            if (backpackHandler.getBackpack() == null) {
                messageHandler.send(player, "backpack.user-not");
                return;
            }

            Inventory inventory = ofMap(
                    messageHandler.get(
                            player,
                            "backpack.title"
                    ),
                    backpackHandler.getBackpack().getContents()
            );

            player.openInventory(inventory);
        });

        return true;
    }

}
