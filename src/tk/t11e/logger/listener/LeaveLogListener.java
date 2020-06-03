package tk.t11e.logger.listener;
// Created by booky10 in Logger (21:57 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaveLogListener extends LogListener {

    public LeaveLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {
        String log = "Player \"%s\" (%s) left the server with quit message \"%s\"";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId().toString(),
                event.getQuitMessage() == null ? "null" : event.getQuitMessage().replace('§', '&').replace('\n', ' ')
        );
        log(Level.INFO, log);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onKick(PlayerKickEvent event) {
        String log = "Player \"%s\" (%s) got kicked with reason \"%s\" and leave message \"%s\"";
        event.getLeaveMessage();
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId(),
                event.getReason().replace('§', '&').replace('\n', ' '),
                event.getLeaveMessage().replace('§', '&').replace('\n', ' ')
        );
        log(Level.WARNING, log);
    }
}