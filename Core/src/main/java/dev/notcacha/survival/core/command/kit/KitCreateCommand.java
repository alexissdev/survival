package dev.notcacha.survival.core.command.kit;

import dev.notcacha.survival.api.exception.ProcessorException;
import dev.notcacha.survival.api.item.SerializableItem;
import dev.notcacha.survival.api.kit.Kit;
import dev.notcacha.survival.api.kit.builder.KitBuilder;
import dev.notcacha.survival.api.processor.ModelProcessor;
import dev.notcacha.survival.core.kit.creator.KitCreatorSettings;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Command(names = {"create", "add"}, permission = "survival.kit.create")
public class KitCreateCommand implements CommandClass {

    private final MessageHandler messageHandler;
    private final ModelProcessor<Kit> cachedModelProcessor;

    @Inject
    public KitCreateCommand(MessageHandler messageHandler, @Named("cached") ModelProcessor<Kit> cachedModelProcessor) {
        this.messageHandler = messageHandler;
        this.cachedModelProcessor = cachedModelProcessor;
    }


    @Command(names = "")
    public boolean create(@Sender Player player, @OptArg String kitId, @OptArg KitCreatorSettings settings) {
        if (kitId == null) {
            messageHandler.send(player, "kit.create.usage");

            return true;
        }

        Map<Integer, SerializableItem> inventoryContents = new HashMap<>();
        Map<Integer, SerializableItem> armorContents = new HashMap<>();

        if (settings != null) {
            if (settings.isUseInventoryContents())  {
                ItemStack[] playerInventoryContents = player.getInventory().getContents();
                for (int i = 0; i < playerInventoryContents.length; i ++) {

                    inventoryContents.put(i, SerializableItem.fromItemStack(playerInventoryContents[i]));

                }
            }

            if (settings.isUseArmorContents()) {
                ItemStack[] playerArmorContents = player.getInventory().getContents();
                for (int i = 0; i < playerArmorContents.length; i ++) {

                    armorContents.put(i, SerializableItem.fromItemStack(playerArmorContents[i]));
                }
            }
        }

        Kit kit = KitBuilder.newBuilder(
                kitId
        ).setInventoryContents(
                inventoryContents
        ).setArmorContents(
                armorContents
        ).build();

        try  {
            cachedModelProcessor.process(kit);
        } catch (ProcessorException ignored) {
            messageHandler.sendReplacing(player, null, "kit.exists", "%kit_id%", kitId);
            return true;
        }

        messageHandler.sendReplacing(player, null, "kit.create.message", "%kit_id%", kitId);
        return true;
    }
}
