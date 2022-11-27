package fr.litopia.services.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerChatEvent {
    Event<PlayerChatEvent> EVENT = EventFactory.createArrayBacked(PlayerChatEvent.class, (listeners) -> (player,message) -> {
        for (PlayerChatEvent listener : listeners) {
            listener.onChat(player, message);
        }
    });

    void onChat(ServerPlayerEntity player, String message);
}
