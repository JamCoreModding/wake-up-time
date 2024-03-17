package io.github.jamalam360.wake_up_time.fabric;

import io.github.jamalam360.wake_up_time.WakeUpTime;
import net.fabricmc.api.ClientModInitializer;

public class WakeUpTimeFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WakeUpTime.init();
	}
}
