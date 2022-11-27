package fr.litopia.services.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerDeathEvent {
    Event<PlayerDeathEvent> EVENT = EventFactory.createArrayBacked(PlayerDeathEvent.class, (listeners) -> (player, damageSource) -> {
        for (PlayerDeathEvent listener : listeners) {
            listener.onPlayerDeath(player, damageSource);
        }
    });

    void onPlayerDeath(ServerPlayerEntity player, DamageSource damageSource);
}
