package fr.litopia.services.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.message.MessageSourceProfile;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {
    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        System.out.println("Connexion de " + player.getName().toString());
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void remove(ServerPlayerEntity player, CallbackInfo ci) {
        System.out.println("Déconnexion du joueur : "+player.getEntityName());
    }

    @Inject(method = "broadcast", at = @At("HEAD"))
    private void broadcast(SignedMessage message, Predicate<ServerPlayerEntity> shouldSendFiltered, @Nullable ServerPlayerEntity sender, MessageSourceProfile sourceProfile, MessageType.Parameters params) {
        System.out.println("MSG : "+message.getContent().getString());
    }
}