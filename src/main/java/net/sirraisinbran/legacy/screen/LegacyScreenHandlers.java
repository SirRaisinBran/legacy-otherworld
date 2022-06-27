package net.sirraisinbran.legacy.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;

public class LegacyScreenHandlers {

    public static ScreenHandlerType<BloomeryScreenHandler> BLOOMERY_SCREEN_HANDLER;
    public static ScreenHandlerType<WorkbenchScreenHandler> WORKBENCH_SCREEN_HANDLER;
    public static ScreenHandlerType<IncantationScreenHandler> INCANTATION_SCREEN_HANDLER;
    public static ScreenHandlerType<ScarletBrainScreenHandler> SCARLET_BRAIN_SCREEN_HANDLER;

    public static void registerScreenhandlers() {
        BLOOMERY_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(LegacyMod.MOD_ID, "bloomery"),
                        BloomeryScreenHandler::new);
        WORKBENCH_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(LegacyMod.MOD_ID, "workbench"),
                        WorkbenchScreenHandler::new);
        INCANTATION_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(LegacyMod.MOD_ID, "incantation"),
                        IncantationScreenHandler::new);
        SCARLET_BRAIN_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(LegacyMod.MOD_ID, "scarlet_brain"),
                        ScarletBrainScreenHandler::new);
    }
}
