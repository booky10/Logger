package tk.t11e.logger.util;
// Created by booky10 in Logger (10:36 03.06.20)

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tk.t11e.logger.main.Main;

import javax.annotation.CheckReturnValue;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogListener implements Listener {

    private final Logger logger;

    public LogListener(Logger logger) {
        this.logger = logger;
    }

    @CheckReturnValue
    public Logger getLogger() {
        return logger;
    }

    public void log(Level level, Object... message) {
        Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> {
            LogEvent event = new LogEvent(getLogger(), level, message.length == 1 ? message[0].toString() : Arrays.toString(message));
            if (event.callEvent())
                event.getLogger().log(event.getLevel(), event.getText());
        });
    }

    public void log(Object message) {
        log(Level.INFO, message);
    }
}