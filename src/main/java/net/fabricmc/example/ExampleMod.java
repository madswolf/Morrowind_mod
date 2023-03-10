package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final String ModName = "tutorial";
	// an instance of our new item
	public static final Item CUSTOM_ITEM =
			Registry.register(Registries.ITEM, new Identifier(ModName, "dank_kush"),
					new Item(new FabricItemSettings()));


	public static final FoodComponent SKOOMA_FOODCOMPONENT = new FoodComponent.Builder()
			.hunger(-1)
			.saturationModifier(-1)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 100)
			.build();
	public static final Item SKOOMA = Registry.register(Registries.ITEM, new Identifier(ModName, "skooma"),
			new Skooma(new FabricItemSettings().food(SKOOMA_FOODCOMPONENT)));

	public static final Identifier SKOOMA_SOUND_ID = new Identifier(ModName, "skooma_sound");
	public static SoundEvent SKOOMA_SOUND = Registry.register(Registries.SOUND_EVENT, ExampleMod.SKOOMA_SOUND_ID, SoundEvent.of(SKOOMA_SOUND_ID));

	@Override
	public void onInitialize() {
	}
}
