package fr.litopia.services.api.mixin;

import fr.litopia.services.api.events.PlayerDeathEvent;
import fr.litopia.services.api.events.PlayerKillPlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getPrimeAdversary()Lnet/minecraft/entity/LivingEntity;"), method = "onDeath")
    private void onPlayerKilled(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof ServerPlayerEntity player) {
            PlayerKillPlayerEvent.EVENT.invoker().onPlayerKillPlayer(player, (ServerPlayerEntity) (Object) this);
        }
    }

    @Inject(at = @At(value = "TAIL"), method = "onDeath")
    private void onPlayerDeath(DamageSource source, CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        PlayerDeathEvent.EVENT.invoker().onPlayerDeath(player, source);
    }
}