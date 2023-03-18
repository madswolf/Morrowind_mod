package net.fabricmc.morrowindmod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoonShineCollectorEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private int number = 0;

    private final int PRODUCTION_TIME = 200;
    public MoonShineCollectorEntity(BlockPos pos, BlockState state) {
        super(MorrowindMod.MOONSHINECOLLECTOR_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new MoonShineCollectorScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        // for 1.19+
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
        // for earlier versions
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        // Save the current value of the number to the nbt
        nbt.putInt("number", number);
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        number = nbt.getInt("number");
        Inventories.readNbt(nbt, items);
    }

    public static void tick(World world, BlockPos pos, BlockState state, MoonShineCollectorEntity be) {
        be.number = be.number + 1;
        if(be.number >= be.PRODUCTION_TIME){
            ItemStack stack = be.getStack(0);
            MorrowindMod.LOGGER.info(stack.getItem().toString());
            MorrowindMod.LOGGER.info(Integer.toString(stack.getCount()));
            MorrowindMod.LOGGER.info(Integer.toString(be.number));
            if(stack.isEmpty()){
                stack = new ItemStack(MorrowindMod.MOONDROP);
            } else {
                stack.increment(1);
            }

            be.setStack(0, stack);
            be.number = be.number - be.PRODUCTION_TIME;
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}
