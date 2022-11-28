package fr.litopia.services.bot.services;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.litopia.services.bot.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListenerService implements EventListener<MessageCreateEvent> {
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        System.out.println("Message received : " + event.getMessage().getContent());
        return null;
    }
}
