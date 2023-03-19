package net.fabricmc.morrowindmod;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class MoonShineFluid extends AbstractFluid {
    @Override
    public Fluid getStill() {
        return MorrowindMod.STILL_MOONSHINE;
    }

    @Override
    public Fluid getFlowing() {
        return MorrowindMod.FLOWING_MOONSHINE;
    }

    @Override
    public Item getBucketItem() {
        return MorrowindMod.MOONSHINE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return MorrowindMod.MOONSHINE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends MoonShineFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends MoonShineFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
