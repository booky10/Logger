package tk.t11e.logger.main;
// Created by booky10 in Logger (16:33 02.06.20)

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static LogHandler handler;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        try {
            handler = new LogHandler(getDataFolder(), getConfig(), this);
        } catch (Throwable throwable) {
            Bukkit.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("Error while creating LogHandler!", throwable);
        }
        try {
            handler.initLoggers();
        } catch (Throwable throwable) {
            Bukkit.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("Error while loading Loggers in LogHandler!", throwable);
        }
    }

    @Override
    public void onEnable() {
        try {
            handler.register(Bukkit.getPluginManager());
        } catch (Throwable throwable) {
            Bukkit.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("Error while registering LogListener in LogHandler!", throwable);
        }
    }
}