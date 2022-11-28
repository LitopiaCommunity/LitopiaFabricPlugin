package fr.litopia.services.bot.services;

import discord4j.core.event.domain.lifecycle.ConnectEvent;
import fr.litopia.services.bot.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BotLoggedListenerService implements EventListener<ConnectEvent> {
    @Override
    public Class<ConnectEvent> getEventType() {
        return ConnectEvent.class;
    }

    @Override
    public Mono<Void> execute(ConnectEvent event) {
        System.out.println("Logged in as " + event.getClient().getSelf().block().getTag());
        return null;
    }
}
