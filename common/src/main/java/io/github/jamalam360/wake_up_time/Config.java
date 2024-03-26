package io.github.jamalam360.wake_up_time;

import io.github.jamalam360.jamlib.config.ConfigExtensions;
import net.minecraft.network.chat.Component;

import java.util.List;

public class Config implements ConfigExtensions<Config> {
	public boolean persistent = false;

	@Override
	public List<Link> getLinks() {
		return List.of(
				new Link(Link.DISCORD, "https://jamalam.tech/Discord", Component.translatable("config.wake_up_time.discord")),
				new Link(Link.GITHUB, "https://github.com/JamCoreModding/wake-up-time", Component.translatable("config.wake_up_time.github")),
				new Link(Link.GENERIC_LINK, "https://modrinth.com/mod/wake-up-time", Component.translatable("config.wake_up_time.modrinth"))
		);
	}
}
