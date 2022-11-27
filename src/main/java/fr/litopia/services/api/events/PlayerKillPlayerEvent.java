package fr.litopia.services.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerKillPlayerEvent {
    Event<PlayerKillPlayerEvent> EVENT = EventFactory.createArrayBacked(PlayerKillPlayerEvent.class, (listeners) -> (killer, killed) -> {
        for (PlayerKillPlayerEvent listener : listeners) {
            listener.onPlayerKillPlayer(killer, killed);
        }
    });

    void onPlayerKillPlayer(ServerPlayerEntity killer, ServerPlayerEntity killed);
}
