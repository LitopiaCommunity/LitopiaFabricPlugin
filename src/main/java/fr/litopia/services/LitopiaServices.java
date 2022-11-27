package fr.litopia.services;

import fr.litopia.services.api.events.PlayerChatEvent;
import fr.litopia.services.api.events.PlayerJoinEvent;
import fr.litopia.services.api.events.PlayerLeaveEvent;
import net.fabricmc.api.DedicatedServerModInitializer;

public class LitopiaServices implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		System.out.println("Litopia Service Initialized");

		PlayerChatEvent.EVENT.register((player, message) ->	System.out.println("Chat event : " + player.getEntityName() + " : " + message));

		PlayerJoinEvent.EVENT.register((player, server) -> System.out.println("Player join event : " + player.getEntityName()));

		PlayerLeaveEvent.EVENT.register((player, server) -> System.out.println("Player leave event : " + player.getEntityName()));
	}
}
