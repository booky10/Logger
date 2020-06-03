package tk.t11e.logger.listener;
// Created by booky10 in Logger (16:35 02.06.20)

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tk.t11e.logger.util.LogListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatLogListener extends LogListener {

    public ChatLogListener(Logger logger) {
        super(logger);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        String log = "Player \"%s\" (%s) send message \"%s\" with format \"%s\"";
        log = String.format(log,
                event.getPlayer().getName(),
                event.getPlayer().getUniqueId(),
                event.getMessage(),
                event.getFormat()
        );
        if (event.isCancelled())
            log(Level.WARNING, log);
        else
            log(Level.INFO, log);
    }
}