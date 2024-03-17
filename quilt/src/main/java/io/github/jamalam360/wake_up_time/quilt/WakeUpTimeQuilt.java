package io.github.jamalam360.wake_up_time.quilt;

import io.github.jamalam360.wake_up_time.WakeUpTime;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class WakeUpTimeQuilt implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		WakeUpTime.init();
	}
}
