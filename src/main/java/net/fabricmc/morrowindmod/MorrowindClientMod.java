package net.fabricmc.morrowindmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class MorrowindClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(MorrowindMod.BOX_SCREEN_HANDLER, MoonShineCollectorScreen::new);
    }
}
