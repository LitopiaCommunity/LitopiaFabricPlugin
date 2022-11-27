package fr.litopia.services.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerLeaveEvent {
    Event<PlayerLeaveEvent> EVENT = EventFactory.createArrayBacked(PlayerLeaveEvent.class, (listeners) -> (player, server) -> {
        for (PlayerLeaveEvent listener : listeners) {
            listener.onPlayerLeaveServer(player, server);
        }
    });

    void onPlayerLeaveServer(ServerPlayerEntity player, MinecraftServer server);
}