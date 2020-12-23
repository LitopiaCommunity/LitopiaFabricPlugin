package fr.litopia.services.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {
    @Inject(method = "onPlayerConnect", at = @At("RETURN"))
    private void playerConnected(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        System.out.println("Connexion du joueur : "+player.getEntityName());
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void playerDisconnected(ServerPlayerEntity player, CallbackInfo ci) {
        String playerStats = ((ServerStatHandlerAccessor) player.getStatHandler()).asString();
        System.out.println(playerStats);
    }

    @Inject(method = "broadcastChatMessage", at = @At("HEAD"))
    private void broadcastChatMessage(Text message, MessageType type, UUID senderUuid,CallbackInfo ci){
        System.out.println("MSG : "+message.getString());
    }
}