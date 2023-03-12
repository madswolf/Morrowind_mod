package net.fabricmc.morrowindmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorrowindMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(net.fabricmc.morrowindmod.MorrowindMod.MOD_ID);
	public static final String MOD_ID = "morrowindmod";
	public static final FoodComponent SKOOMA_FOODCOMPONENT = new FoodComponent.Builder()
			.hunger(-4)
			.saturationModifier(0.6F)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200), 100)
			.build();
	public static final Item SKOOMA = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "skooma"),
			new Skooma(new FabricItemSettings().maxCount(16).food(SKOOMA_FOODCOMPONENT).rarity(Rarity.EPIC)));

	public static final FoodComponent MOONSUGAR_FOODCOMPONENT = new FoodComponent.Builder()
			.hunger(-20)
			.saturationModifier(0.8F)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 30), 100)
			.build();
	public static final Item MOONSUGAR = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "moonsugar"),
			new MoonSugar(new FabricItemSettings().food(MOONSUGAR_FOODCOMPONENT).rarity(Rarity.UNCOMMON)));
	public static final Identifier SKOOMA_SOUND_ID = new Identifier(MOD_ID, "skooma_sound");
	public static final Identifier MOONSUGAR_SOUND_ID = new Identifier(MOD_ID, "moonsugar_sound");
	public static final SoundEvent SKOOMA_SOUND = Registry.register(Registry.SOUND_EVENT, SKOOMA_SOUND_ID, new SoundEvent(SKOOMA_SOUND_ID));

	public static final SoundEvent MOONSUGAR_SOUND = Registry.register(Registry.SOUND_EVENT, MOONSUGAR_SOUND_ID, new SoundEvent(MOONSUGAR_SOUND_ID));

	//private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(ModName, String.format("%1$s_group",ModName)))
	//		.icon(() -> new ItemStack(SKOOMA))
	//		.build();

	public static final Identifier MOONSHINECOLLECTOR_ID = new Identifier(MOD_ID, "moonshinecollector");


	public static final Block MoonShineCollector = Registry.register(Registry.BLOCK, MOONSHINECOLLECTOR_ID, new Block(FabricBlockSettings.of(Material.AMETHYST).strength(1.0f)));

	public static final Item MOONSHINECOLLECTOR_ITEM = Registry.register(Registry.ITEM, MOONSHINECOLLECTOR_ID, new BlockItem(MoonShineCollector, new FabricItemSettings()));

	@Override
	public void onInitialize() {
		//ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
		//	content.add(SKOOMA);
		//	content.add(MOONSUGAR);
		//	content.add(CUSTOM_ITEM);
		//});
	}
}
