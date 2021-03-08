package dev.notcacha.survival.api.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.notcacha.survival.api.util.Colorize;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Item that can be built to an {@link org.bukkit.inventory.ItemStack}
 */

public interface SerializableItem {

    /**
     * @return material of the item.
     */

    @JsonProperty("type")
    String getMaterialName();

    /**
     * @return item quantity
     */

    @JsonProperty("amount")
    Integer getNumber();

    /**
     * @return The item decoration.
     */

    @JsonProperty("decoration")
    DecorationCompound getDecoration();

    interface DecorationCompound {

        /**
         * @return The display name from item.
         */

        @JsonProperty("display_name")
        String getDisplayName();

        /**
         * @return The description from item.
         */

        @JsonProperty("description")
        List<String> getDescription();

    }

    /**
     * @return enchantments of the item
     */

    @JsonProperty("enchantments")
    Set<EnchantmentCompound> getEnchantments();

    interface EnchantmentCompound {

        /**
         * @return enchantment to be applied
         */

        @JsonProperty("enchantment_type")
        String getType();

        /**
         * @return level of the enchantment
         */

        @JsonProperty("enchantment_level")
        int getLevel();

    }

    /**
     * Transform this class to ItemStack
     *
     * @return new ItemStack instance.
     */

    @JsonIgnore
    default ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(Material.matchMaterial(getMaterialName()), getNumber());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(
                Colorize.colorize(
                        getDecoration().getDisplayName()
                )
        );
        itemMeta.setLore(
                Colorize.colorize(
                        getDecoration().getDescription()
                )
        );

        for (EnchantmentCompound enchantmentCompound : getEnchantments()) {
            Enchantment type = Enchantment.getByName(enchantmentCompound.getType());
            if (type != null) {
                itemMeta.addEnchant(type, enchantmentCompound.getLevel(), true);
            }
        }

        return itemStack;
    }

    /**
     * Transform ItemStack to SerializableItem
     *
     * @return New Serializable item.
     */

    static SerializableItem fromItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        return new SerializableItem() {
            @Override
            public String getMaterialName() {
                return itemStack.getType().name();
            }

            @Override
            public Integer getNumber() {
                return itemStack.getAmount();
            }

            @Override
            public DecorationCompound getDecoration() {
                return new DecorationCompound() {
                    @Override
                    public String getDisplayName() {
                        return itemMeta.getDisplayName();
                    }

                    @Override
                    public List<String> getDescription() {
                        return itemMeta.getLore();
                    }
                };
            }

            @Override
            public Set<EnchantmentCompound> getEnchantments() {
                Set<EnchantmentCompound> enchantmentCompoundSet = new HashSet<>();

                itemMeta.getEnchants().forEach((enchantment, level) -> {
                    enchantmentCompoundSet.add(new EnchantmentCompound() {
                        @Override
                        public String getType() {
                            return enchantment.getName();
                        }

                        @Override
                        public int getLevel() {
                            return level;
                        }
                    });
                });

                return enchantmentCompoundSet;
            }

        };
    }

}
