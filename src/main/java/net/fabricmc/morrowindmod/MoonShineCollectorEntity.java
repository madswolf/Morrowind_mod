package net.fabricmc.morrowindmod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoonShineCollectorEntity extends BlockEntity implements ImplementedInventory{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int number = 7;

    private int productionTime = 200;
    public MoonShineCollectorEntity(BlockPos pos, BlockState state) {
        super(MorrowindMod.MOONSHINECOLLECTOR_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
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
        if(be.number >= be.productionTime){
            ItemStack stack = be.getStack(0);
            if(stack.isEmpty()){
                stack = new ItemStack(MorrowindMod.MOONDROP);
            } else {
                stack.increment(1);
            }

            be.setStack(0, stack);
            be.number = be.number - be.productionTime;
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
