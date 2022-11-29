package fr.litopia.services.bot.services;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ConnectEvent;
import discord4j.core.object.entity.Webhook;
import fr.litopia.services.api.events.PlayerChatEvent;
import fr.litopia.services.api.events.PlayerDeathEvent;
import fr.litopia.services.api.events.PlayerJoinEvent;
import fr.litopia.services.api.events.PlayerLeaveEvent;
import fr.litopia.services.bot.BotConfiguration;
import fr.litopia.services.bot.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class ChatReplicantService implements EventListener<ConnectEvent> {
    private static final Logger log = LoggerFactory.getLogger(BotConfiguration.class);
    private static final String MC_HEAD_URL = "https://crafatar.com/avatars/";

    @Value("${discord-bot.replicant.webhook.id}")
    private String secretWebhookId;

    @Value("${discord-bot.replicant.webhook.token}")
    private String secretWebhookToken;

    @Override
    public Class<ConnectEvent> getEventType() {
        return ConnectEvent.class;
    }

    @Override
    public Mono<Void> execute(ConnectEvent event) {
        // @todo : prevent thread starvation by using a thread pool
        GatewayDiscordClient client = event.getClient();
        String defaultAvatarUrl = Objects.requireNonNull(client.getSelf().block()).getAvatarUrl();
        String botName = Objects.requireNonNull(client.getSelf().block()).getUsername();
        Webhook webhook = client.getWebhookByIdWithToken(Snowflake.of(secretWebhookId), secretWebhookToken).block();
        if (webhook == null) {
            log.error("Unable to find webhook with id " + secretWebhookId);
            return Mono.empty();
        }

        PlayerJoinEvent.EVENT.register(((player, server) -> webhook.execute()
                .withAvatarUrl(defaultAvatarUrl)
                .withUsername(botName)
                .withContent("\uD83D\uDC4B **" + player.getName().getString() + "** à rejoint Litopia [" + (Objects.requireNonNull(server.getServerMetadata().getPlayers()).getOnlinePlayerCount()+1)+"/"+server.getServerMetadata().getPlayers().getPlayerLimit()+"]")
                .block()));

        PlayerLeaveEvent.EVENT.register(((player, server) -> webhook.execute()
                .withAvatarUrl(defaultAvatarUrl)
                .withUsername(botName)
                .withContent("\uD83D\uDEAA **" + player.getName().getString() + " à quitté Litopia** [" + (Objects.requireNonNull(server.getServerMetadata().getPlayers()).getOnlinePlayerCount()-1)+"/"+server.getServerMetadata().getPlayers().getPlayerLimit()+"]")
                .block()));

        PlayerChatEvent.EVENT.register(((player, message) -> webhook.execute()
                .withAvatarUrl(MC_HEAD_URL +player.getUuidAsString())
                .withUsername(player.getName().getString())
                .withContent(message)
                .block()));

        PlayerDeathEvent.EVENT.register(((player, message) -> webhook.execute()
                .withAvatarUrl(MC_HEAD_URL +player.getUuidAsString())
                .withUsername(player.getName().getString())
                .withContent("\uD83D\uDC80 **"+ message.getDeathMessage(player).getString()+"**")
                .block()));

        return Mono.empty();
    }
}
