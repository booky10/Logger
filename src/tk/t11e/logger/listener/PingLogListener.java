package tk.t11e.logger.listener;
// Created by booky10 in Logger (21:57 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PingLogListener extends LogListener {

    public PingLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPing(ServerListPingEvent event) {
        String log = "Address \"%s\" (%s) pinged server with response \"%s\" max players, \"%s\" current players and \"%s\" as the MOTD";
        log = String.format(log,
                event.getAddress().toString(),
                event.getAddress().getHostName(),
                event.getMaxPlayers(),
                event.getNumPlayers(),
                event.getMotd().replace('§', '&').replace('\n', ' ')
        );
        log(Level.INFO, log);
    }
}