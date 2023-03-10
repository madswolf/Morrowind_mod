package net.fabricmc.example;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.PotionItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Skooma extends Item {

    public Skooma(Settings settings) {
        super(settings);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }
    @Override
    public SoundEvent getEatSound(){
        return ExampleMod.SKOOMA_SOUND;
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
        return ExampleMod.SKOOMA_SOUND;
    }

    @Override
     public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        //user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200));

        return super.finishUsing(stack,world,user);
    }
}
