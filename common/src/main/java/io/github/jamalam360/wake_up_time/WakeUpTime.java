package io.github.jamalam360.wake_up_time;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import io.github.jamalam360.jamlib.JamLib;
import io.github.jamalam360.jamlib.JamLibPlatform;
import io.github.jamalam360.jamlib.config.ConfigManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

public class WakeUpTime {
	public static final String MOD_ID = "wake_up_time";
	public static final String MOD_NAME = "Wake Up Time";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final ConfigManager<Config> CONFIG = new ConfigManager<>(MOD_ID, Config.class);
	private static final KeyMapping sendStatusToActionBar = new KeyMapping("key.wake_up_time.status", GLFW_KEY_V, "category.wake_up_time");
	private static boolean enabled = false;

	public static void init() {
		LOGGER.info("Initializing Wake Up Time on " + JamLibPlatform.getPlatform().name());

		if (JamLibPlatform.getPlatform() == JamLibPlatform.Platform.FABRIC || JamLibPlatform.getPlatform() == JamLibPlatform.Platform.QUILT) {
			Path configFolder = Platform.getConfigFolder();
			Path oldConfig = configFolder.resolve("wake_up_time.json");
			Path newConfig = configFolder.resolve("wake_up_time.json5");

			if (oldConfig.toFile().exists()) {
				LOGGER.info("Found an legacy config file, recovering it");

				if (newConfig.toFile().exists()) {
					LOGGER.warn("Found a new config file, not recovering the legacy config");
				} else {
					if (oldConfig.toFile().renameTo(newConfig.toFile())) {
						LOGGER.info("Recovered the legacy config file");
					} else {
						LOGGER.error("Failed to recover the legacy config file");
					}
				}

			}
		}

		JamLib.checkForJarRenaming(WakeUpTime.class);
		KeyMappingRegistry.register(sendStatusToActionBar);
		ClientTickEvent.CLIENT_LEVEL_POST.register((level) -> {
			if (sendStatusToActionBar.consumeClick()) {
				enabled = !enabled;
				sendStatusToActionBar(level);
			}

			if (CONFIG.get().persistent && enabled) {
				sendStatusToActionBar(level);
			}
		});
	}

	private static void sendStatusToActionBar(ClientLevel level) {
		if (Minecraft.getInstance().player != null) {
			Minecraft.getInstance().player.displayClientMessage(Component.translatable("text.wake_up_time.status", getStageComponent(level)), true);
		}
	}

	private static Component getStageComponent(ClientLevel level) {
		long time = level.getDayTime();
		while (time > 24000) time = time - 24000;

		if (time < 9000 && time >= 2000) {
			return Component.translatable("text.wake_up_time.working", "§a" + (9000 - time) / 20);
		}

		Component status = Component.translatable("text.wake_up_time.wandering");
		if (time >= 12000 || time < 10) status = Component.translatable("text.wake_up_time.sleeping");
		if (time >= 9000 && time < 11000) status = Component.translatable("text.wake_up_time.gathering");

		final long timeUntilWork = time > 9000 ? 26000 - time : 2000 - time;

		return Component.translatable("text.wake_up_time.lazy_bums", "§a" + status.getString(), "§a" + timeUntilWork / 20);
	}
}
