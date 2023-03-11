package net.fabricmc.morrowindmod;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class Skooma extends Item {

    public Skooma(Settings settings) {
        super(settings);
    }
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }
    @Override
    public SoundEvent getEatSound(){
        return net.fabricmc.morrowindmod.MorrowindMod.SKOOMA_SOUND;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return net.fabricmc.morrowindmod.MorrowindMod.SKOOMA_SOUND;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        // default white text
        tooltip.add(new TranslatableText(String.format("item.%s.skooma.tooltip", net.fabricmc.morrowindmod.MorrowindMod.MOD_ID)));

        // formatted red text
        tooltip.add(new TranslatableText(String.format("item.%s.skooma.tooltip.red", net.fabricmc.morrowindmod.MorrowindMod.MOD_ID)).formatted(Formatting.RED));
    }
}
