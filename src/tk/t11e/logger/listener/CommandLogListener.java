package tk.t11e.logger.listener;
// Created by booky10 in Logger (16:35 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLogListener extends LogListener {


    public CommandLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String log = "Player \"%s\" (%s) send command \"%s\"";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId(),
                event.getMessage()
        );
        if (event.isCancelled())
            log(Level.WARNING, log);
        else
            log(Level.INFO, log);
    }

    @EventHandler
    public void onRCONCommand(RemoteServerCommandEvent event) {
        String log = "RCON send command \"/%s\"";
        log = String.format(log,
                event.getCommand()
        );
        if (event.isCancelled())
            log(Level.WARNING, log);
        else
            log(Level.INFO, log);
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        String log = "Console send command \"/%s\"";
        log = String.format(log,
                event.getCommand()
        );
        if (event.isCancelled())
            log(Level.WARNING, log);
        else
            log(Level.INFO, log);
    }
}