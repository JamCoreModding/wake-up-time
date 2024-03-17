package io.github.jamalam360.wake_up_time.neoforge;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.github.jamalam360.wake_up_time.WakeUpTime;
import net.neoforged.fml.common.Mod;

@Mod(WakeUpTime.MOD_ID)
public class WakeUpTimeNeoForge {
    public WakeUpTimeNeoForge() {
        EnvExecutor.runInEnv(Env.CLIENT, () -> WakeUpTime::init);
    }
}
