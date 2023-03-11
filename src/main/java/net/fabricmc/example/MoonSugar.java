package net.fabricmc.example;


import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MoonSugar extends Item {

    public MoonSugar(Settings settings) {
        super(settings);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }
    @Override
    public SoundEvent getEatSound(){
        return ExampleMod.MOONSUGAR_SOUND;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return ExampleMod.MOONSUGAR_SOUND;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        // default white text
        tooltip.add(new TranslatableText("item.tutorial.moonsugar.tooltip"));

        // formatted red text
        tooltip.add(new TranslatableText("item.tutorial.moonsugar.tooltip.red").formatted(Formatting.RED));
    }
}