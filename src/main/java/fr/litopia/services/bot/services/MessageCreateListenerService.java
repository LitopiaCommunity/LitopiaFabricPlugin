package fr.litopia.services.bot.services;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.litopia.services.api.utils.FabricUtils;
import fr.litopia.services.bot.event.EventListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListenerService implements EventListener<MessageCreateEvent> {
    @Value("${discord-bot.replicant.channel}")
    private String chatChannel;
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        if (event.getMessage().getAuthor().isEmpty())return Mono.empty();
        if (event.getMessage().getAuthor().get().isBot())return Mono.empty();
        if (!event.getMessage().getChannel().block().getId().asString().equals(chatChannel))return Mono.empty();
        System.out.println("Message received : " + event.getMessage().getContent());
        MinecraftServer server = FabricUtils.getServer();
        if (server != null) {
            String userName = event.getMessage().getAuthor().get().getUsername();
            String message = event.getMessage().getContent();
            MutableText mcMessage = Text.literal("[Discord] <" + userName + "> " + message);
            server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity ->
                    serverPlayerEntity.sendMessage(mcMessage, false));
        }
        return Mono.empty();
    }
}
