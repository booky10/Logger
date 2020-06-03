package tk.t11e.logger.listener;
// Created by booky10 in Logger (22:00 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TeleportLogListener extends LogListener {

    public TeleportLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTeleport(PlayerTeleportEvent event) {
        String log = "Player \"%s\" (%s) teleport with reason \"%s\" from \"%s, %s, %s\" in world \"%s\" to \"%s, %s, %s\" in world \"%s\"";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId().toString(),
                event.getCause().name().toLowerCase().replace('_', ' '),
                event.getFrom().getX(),
                event.getFrom().getY(),
                event.getFrom().getZ(),
                event.getFrom().getWorld().getName(),
                event.getTo().getX(),
                event.getTo().getY(),
                event.getTo().getZ(),
                event.getTo().getWorld().getName()
        );
        if (event.isCancelled())
            log(Level.WARNING, log);
        else
            log(Level.INFO, log);
    }
}