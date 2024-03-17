package io.github.jamalam360.wake_up_time.forge;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.github.jamalam360.wake_up_time.WakeUpTime;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WakeUpTime.MOD_ID)
public class WakeUpTimeForge {
    public WakeUpTimeForge() {
        EventBuses.registerModEventBus(WakeUpTime.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        EnvExecutor.runInEnv(Env.CLIENT, () -> WakeUpTime::init);
    }
}
