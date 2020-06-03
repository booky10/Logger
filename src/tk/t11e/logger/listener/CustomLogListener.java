package tk.t11e.logger.listener;
// Created by booky10 in Logger (16:35 02.06.20)

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import tk.t11e.logger.util.LogListener;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogListener extends LogListener {

    private final List<String> events;

    public CustomLogListener(Logger logger, List<String> events) {
        super(logger);
        this.events = events;
        if (events.contains("LogEvent"))
            throw new IllegalArgumentException("You've tried to specify the \"LogEvent\", so the event logs itself... This doesn't work, please don't try it!");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(Event event) {
        if (events.contains(event.getEventName()))
            log(Level.INFO, event.toString());
    }
}