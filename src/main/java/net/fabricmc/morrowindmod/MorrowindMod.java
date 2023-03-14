package net.fabricmc.morrowindmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorrowindMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(net.fabricmc.morrowindmod.MorrowindMod.MOD_ID);
	public static final String MOD_ID = "morrowindmod";

	private static final ItemGroup MORROWIND_ITEMGROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, String.format("%1$s_group", MOD_ID)))
			.icon(() -> new ItemStack(Blocks.AMETHYST_BLOCK))
			.build();
	public static final FoodComponent SKOOMA_FOODCOMPONENT = new FoodComponent.Builder()
			.hunger(-4)
			.saturationModifier(0.6F)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200), 100)
			.build();
	public static final Item SKOOMA = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "skooma"),
			new Skooma(new FabricItemSettings().group(MORROWIND_ITEMGROUP).maxCount(16).food(SKOOMA_FOODCOMPONENT).rarity(Rarity.EPIC)));

	public static final FoodComponent MOONSUGAR_FOODCOMPONENT = new FoodComponent.Builder()
			.hunger(-20)
			.saturationModifier(0.8F)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 100)
			.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 30), 100)
			.build();
	public static final Item MOONSUGAR = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "moonsugar"),
			new MoonSugar(new FabricItemSettings().group(MORROWIND_ITEMGROUP).food(MOONSUGAR_FOODCOMPONENT).rarity(Rarity.UNCOMMON)));
	public static final Identifier SKOOMA_SOUND_ID = new Identifier(MOD_ID, "skooma_sound");
	public static final Identifier MOONSUGAR_SOUND_ID = new Identifier(MOD_ID, "moonsugar_sound");
	public static final SoundEvent SKOOMA_SOUND = Registry.register(Registry.SOUND_EVENT, SKOOMA_SOUND_ID, new SoundEvent(SKOOMA_SOUND_ID));

	public static final SoundEvent MOONSUGAR_SOUND = Registry.register(Registry.SOUND_EVENT, MOONSUGAR_SOUND_ID, new SoundEvent(MOONSUGAR_SOUND_ID));

	public static final Identifier MOONSHINECOLLECTOR_ID = new Identifier(MOD_ID, "moonshinecollector");


	public static final Block MOONSHINECOLLECTOR = Registry.register(Registry.BLOCK, MOONSHINECOLLECTOR_ID, new MoonshineCollector(FabricBlockSettings.of(Material.AMETHYST).strength(1.0f)));

	public static final Item MOONSHINECOLLECTOR_ITEM = Registry.register(Registry.ITEM, MOONSHINECOLLECTOR_ID, new BlockItem(MOONSHINECOLLECTOR, new FabricItemSettings().group(MORROWIND_ITEMGROUP)));

	public static final Identifier MOONSHINECOLLECTOR_BLOCK_ENTITY_ID = new Identifier(MOD_ID, "moonshinecollector_block_entity");
	public static BlockEntityType<MoonShineCollectorEntity> MOONSHINECOLLECTOR_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			MOONSHINECOLLECTOR_BLOCK_ENTITY_ID,
			FabricBlockEntityTypeBuilder.create(MoonShineCollectorEntity::new, MOONSHINECOLLECTOR).build()
	);

	public static final Identifier MOONDROP_ID = new Identifier(MOD_ID, "moondrop");

	public static final Item MOONDROP = Registry.register(Registry.ITEM, MOONDROP_ID,
			new Item(new FabricItemSettings().group(MORROWIND_ITEMGROUP).maxCount(1).rarity(Rarity.UNCOMMON)));

	public static final Identifier MOONSEED_ID = new Identifier(MOD_ID, "moonseed");

	public static final Item MOONSEED = Registry.register(Registry.ITEM, MOONSEED_ID,
			new Item(new FabricItemSettings().group(MORROWIND_ITEMGROUP).maxCount(1).rarity(Rarity.UNCOMMON)));
	@Override
	public void onInitialize() {
	}
}
