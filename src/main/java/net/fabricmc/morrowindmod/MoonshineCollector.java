package net.fabricmc.morrowindmod;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MoonshineCollector extends BlockWithEntity implements BlockEntityProvider {

    public MoonshineCollector(Settings settings) {
        super(settings);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, MorrowindMod.MOONSHINECOLLECTOR_BLOCK_ENTITY, MoonShineCollectorEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MoonShineCollectorEntity(pos,state);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) return ActionResult.SUCCESS;
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);

        MorrowindMod.LOGGER.info(blockEntity.getStack(0).getItem().toString());

        if (player.getStackInHand(hand).isEmpty()) {
            // If the player is not holding anything we'll get give him the items in the block entity one by one
            MorrowindMod.LOGGER.info("Hand empty");
            // Find the first slot that has an item and give it to the player
            if (!blockEntity.getStack(0).isEmpty()) {
                // Give the player the stack in the inventory
                player.getInventory().offerOrDrop(blockEntity.getStack(0));
                // Remove the stack from the inventory
                blockEntity.removeStack(0);
            }
        }
        return ActionResult.SUCCESS;
    }
}
