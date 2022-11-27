package fr.litopia.services.api.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(at = @At("HEAD"),method = "Lnet/minecraft/server/world/ServerWorld;emitGameEvent(Lnet/minecraft/world/event/GameEvent;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/event/GameEvent$Emitter;)V")
    void emitGameEvent(GameEvent event, Vec3d emitterPos, GameEvent.Emitter emitter, CallbackInfo ci) {
        /*if (event == GameEvent.BLOCK_DESTROY || event == GameEvent.BLOCK_PLACE || event == GameEvent.BLOCK_ATTACH || event == GameEvent.BLOCK_DETACH) {
            System.out.println("emitGameEvent");
            System.out.println(event.getId());
            if (emitter.affectedState()!=null) {
                System.out.println(emitter.affectedState().getBlock().toString());
            }
            System.out.println(emitterPos.toString());
        }*/
        // à voir si on peut faire quelque chose avec ça
    }
}
