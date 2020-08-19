package io.ace.nord.hacks.combat;

import io.ace.nord.hacks.Hack;

import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class AutoTotem extends Hack {

    boolean strict = false;
    int delay = 0;
    int totems;
    int totemsOffHand;
    int totemSwtichDelay = 0;

    public AutoTotem() {
        super("AutoTotem", Category.COMBAT);
    }

    public void setup() {

    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (mc.world == null)
            return;
        totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        totemsOffHand = mc.player.inventory.offHandInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

        if (strict) {

            if (mc.player.isSprinting() && mc.player.getHeldItemOffhand().isEmpty() && totems > 0) {
                mc.player.setSprinting(false);
            }
        }
        for (int i = 0; i < 45; i++) {
            if (totems + totemsOffHand > 0) {
                ItemStack stacks = mc.player.openContainer.getSlot(i).getStack();

                if (stacks == ItemStack.EMPTY)
                    continue;
//
                Item itemTotem = Items.TOTEM_OF_UNDYING;
                if (mc.player.getHeldItemOffhand().isEmpty()) {
                    totemSwtichDelay++;
                    if (!(mc.currentScreen instanceof GuiCrafting) && !(mc.currentScreen instanceof GuiFurnace) && !(mc.currentScreen instanceof GuiBeacon) && !(mc.currentScreen instanceof GuiBrewingStand) && !(mc.currentScreen instanceof GuiChest) && !(mc.currentScreen instanceof GuiCommandBlock) && !(mc.currentScreen instanceof GuiDispenser) && !(mc.currentScreen instanceof GuiEnchantment && !(mc.currentScreen instanceof GuiShulkerBox) && !(mc.currentScreen instanceof GuiContainerCreative))) {
                        if (stacks.getItem() == itemTotem) {
                            if (totemSwtichDelay >= delay) {
                                mc.playerController.windowClick(0, i, 1, ClickType.PICKUP, mc.player);
                                mc.playerController.windowClick(0, 45, 1, ClickType.PICKUP, mc.player);
                                totemSwtichDelay = 0;
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public void onEnable() {
        totemSwtichDelay = 0;
    }
}