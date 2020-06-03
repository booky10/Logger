package tk.t11e.logger.listener;
// Created by booky10 in Logger (21:57 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JoinLogListener extends LogListener {

    public JoinLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        String log = "Player \"%s\" (%s) joined the server with message \"%s\"";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId().toString(),
                event.getJoinMessage() == null ? "null" : event.getJoinMessage().replace('§', '&').replace('\n', ' ')
        );
        log(Level.INFO, log);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent event) {
        String log = "Player \"%s\" (%s) tried to log in with address \"%s\" (%s) or \"%s\" (%s), result \"%s\" (KickMessage: %s)";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId().toString(),
                event.getAddress().toString(),
                event.getAddress().getHostName(),
                event.getRealAddress().toString(),
                event.getRealAddress().getHostName(),
                event.getResult().name().toLowerCase().replace('_', ' '),
                event.getKickMessage().replace('§', '&').replace('\n', ' ')
        );
        if (event.getResult() == PlayerLoginEvent.Result.ALLOWED)
            log(Level.INFO, log);
        else
            log(Level.WARNING, log);
    }
}