package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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

	// an instance of our new item
	public static final Item CUSTOM_ITEM =
			Registry.register(Registries.ITEM, new Identifier("tutorial", "dank_kush"),
					new Item(new FabricItemSettings()));

	public static final Item SKOOMA =
			Registry.register(Registries.ITEM, new Identifier("tutorial", "skooma"),
					new Skooma(new FabricItemSettings()));


	public static final Identifier SKOOMA_SOUND_ID = new Identifier("tutorial", "skooma_sound");
	public static SoundEvent SKOOMA_SOUND = Registry.register(Registries.SOUND_EVENT, ExampleMod.SKOOMA_SOUND_ID, SoundEvent.of(SKOOMA_SOUND_ID));

	@Override
	public void onInitialize() {
	}
}
