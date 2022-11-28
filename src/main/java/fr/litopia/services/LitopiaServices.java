package fr.litopia.services;

import fr.litopia.services.api.events.*;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.springframework.boot.SpringApplication;

public class LitopiaServices implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		System.out.println("Litopia Service Initialized");

		//PlayerChatEvent.EVENT.register((player, message) ->	System.out.println("Chat event : " + player.getEntityName() + " : " + message));

		//PlayerJoinEvent.EVENT.register((player, server) -> System.out.println("Player join event : " + player.getEntityName()));

		PlayerLeaveEvent.EVENT.register((player, server) -> System.out.println("Player leave event : " + player.getEntityName()));

		PlayerDeathEvent.EVENT.register((player, damageSource) -> System.out.println("Player death event : " + player.getEntityName()+ " with death message : " + damageSource.getDeathMessage(player).getString()));

		PlayerKillPlayerEvent.EVENT.register((killer, killed) -> System.out.println("Player kill player event : " + killed.getEntityName() + " killed by " + killer.getName().getString()));

		SpringApplication.run(LitopiaSpring.class);
	}
}
