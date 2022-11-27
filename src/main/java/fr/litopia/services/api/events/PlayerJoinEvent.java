package fr.litopia.services.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
public interface PlayerJoinEvent {
    Event<PlayerJoinEvent> EVENT = EventFactory.createArrayBacked(PlayerJoinEvent.class, (listeners) -> (player, server) -> {
        for (PlayerJoinEvent listener : listeners) {
            listener.onPlayerJoinServer(player, server);
        }
    });

    void onPlayerJoinServer(ServerPlayerEntity player, MinecraftServer server);
}
