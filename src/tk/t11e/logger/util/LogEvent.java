package tk.t11e.logger.util;
// Created by booky10 in Logger (11:46 03.06.20)

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import org.apache.commons.lang.SerializationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.CheckReturnValue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogEvent extends Event implements Cancellable, ConfigurationSerializable {

    private boolean cancelled;
    private Logger logger;
    private Level level;
    private String text;

    public LogEvent(@NotNull Logger logger, @NotNull Level level, @NotNull String text) {
        cancelled = false;
        this.logger = logger;
        this.level = level;
        this.text = text;
    }

    private LogEvent(boolean cancelled, Logger logger, Level level, String text) {
        this.cancelled = cancelled;
        this.logger = logger;
        this.level = level;
        this.text = text;
    }

    @NotNull
    @CheckReturnValue
    public static LogEvent deserialize(Map<String, Object> serialized) {
        try {
            boolean cancelled = (boolean) serialized.get("cancelled");
            Logger logger = (Logger) serialized.get("logger");
            Level level = (Level) serialized.get("level");
            String text = (String) serialized.get("text");

            return new LogEvent(cancelled, logger, level, text);
        } catch (Throwable throwable) {
            throw new DeserializationException("Error while deserializing", throwable);
        }
    }

    @CheckReturnValue
    @NotNull
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(@NotNull Logger logger) {
        this.logger = logger;
    }

    @CheckReturnValue
    @NotNull
    public Level getLevel() {
        return level;
    }

    public void setLevel(@NotNull Level level) {
        this.level = level;
    }

    @CheckReturnValue
    @NotNull
    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    @Override
    @CheckReturnValue
    @NotNull
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(@NotNull boolean cancelled) {
        this.cancelled = cancelled;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    @Deprecated
    public HandlerList getHandlers() {
        return new HandlerList();
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "cancelled=" + cancelled +
                ", level=" + level +
                ", text='" + text + '\'' +
                '}';
    }

    @SuppressWarnings("NullableProblems")
    @NotNull
    @CheckReturnValue
    public Map<String, Object> serialize() {
        try {
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("cancelled", cancelled);
            result.put("logger", logger);
            result.put("level", level);
            result.put("text", text);
            return result;
        } catch (Throwable throwable) {
            throw new SerializationException("Error while serializing", throwable);
        }
    }
}