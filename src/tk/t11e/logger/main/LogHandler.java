package tk.t11e.logger.main;
// Created by booky10 in Logger (16:38 02.06.20)

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tk.t11e.logger.listener.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@SuppressWarnings("ResultOfMethodCallIgnored")
class LogHandler {

    private final File customFolder, chatFolder, commandFolder, joinFolder, leaveFolder, teleportFolder, pingFolder;
    private final Logger customLogger = Logger.getLogger("EventLogger");
    private final Logger chatLogger = Logger.getLogger("ChatLogger");
    private final Logger commandLogger = Logger.getLogger("CommandLogger");
    private final Logger joinLogger = Logger.getLogger("JoinLogger");
    private final Logger leaveLogger = Logger.getLogger("LeaveLogger");
    private final Logger teleportLogger = Logger.getLogger("TeleportLogger");
    private final Logger pingLogger = Logger.getLogger("PingLogger");
    private final FileConfiguration config;
    private final JavaPlugin plugin;
    private final String format;

    public LogHandler(File parentFolder, FileConfiguration config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
        format = config.getString("logFormat");

        String customFolderName = config.getString("logEventsFolder");
        String chatFolderName = config.getString("logChatFolder");
        String commandFolderName = config.getString("logCommandsFolder");
        String joinFolderName = config.getString("logEventsFolder");
        String leaveFolderName = config.getString("logChatFolder");
        String teleportFolderName = config.getString("logCommandsFolder");
        String pingFolderName = config.getString("logPingsFolder");

        customFolder = new File(parentFolder + File.separator + customFolderName);
        chatFolder = new File(parentFolder + File.separator + chatFolderName);
        commandFolder = new File(parentFolder + File.separator + commandFolderName);
        joinFolder = new File(parentFolder + File.separator + joinFolderName);
        leaveFolder = new File(parentFolder + File.separator + leaveFolderName);
        teleportFolder = new File(parentFolder + File.separator + teleportFolderName);
        pingFolder = new File(parentFolder + File.separator + pingFolderName);

        if (!customFolder.exists())
            customFolder.mkdirs();
        if (!chatFolder.exists())
            chatFolder.mkdirs();
        if (!commandFolder.exists())
            commandFolder.mkdirs();
        if (!joinFolder.exists())
            joinFolder.mkdirs();
        if (!leaveFolder.exists())
            leaveFolder.mkdirs();
        if (!teleportFolder.exists())
            teleportFolder.mkdirs();
        if (!pingFolder.exists())
            pingFolder.mkdirs();
    }

    public String getFormat() {
        return format;
    }

    public void register(PluginManager pluginManager) {
        if (config.getBoolean("logEvents"))
            pluginManager.registerEvents(new CustomLogListener(getCustomLogger(), config.getStringList("logEventsEvents")), plugin);
        if (config.getBoolean("logChat"))
            pluginManager.registerEvents(new ChatLogListener(getChatLogger()), plugin);
        if (config.getBoolean("logCommands"))
            pluginManager.registerEvents(new CommandLogListener(getCommandLogger()), plugin);
        if (config.getBoolean("logJoins"))
            pluginManager.registerEvents(new JoinLogListener(getJoinLogger()), plugin);
        if (config.getBoolean("logLefts"))
            pluginManager.registerEvents(new LeaveLogListener(getLeaveLogger()), plugin);
        if (config.getBoolean("logTeleports"))
            pluginManager.registerEvents(new TeleportLogListener(getTeleportLogger()), plugin);
        if (config.getBoolean("logPings"))
            pluginManager.registerEvents(new PingLogListener(getPingLogger()), plugin);
    }

    public void initLoggers() {
        String fileName = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + ".log";
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord record) {
                String format = getFormat();
                String logTime = new SimpleDateFormat("HH:mm:ss").format(new Date(record.getMillis()));
                String logDate = new SimpleDateFormat("dd.MM.yyy").format(new Date(record.getMillis()));
                format = format.replace("%TIME%", logTime);
                format = format.replace("%DATE%", logDate);
                format = format.replace("%LEVEL%", record.getLevel().getName());
                format = format.replace("%NAME%", record.getLoggerName());
                return format + "\n";
            }
        };

        if (config.getBoolean("logEvents")) {
            try {
                FileHandler fileHandler = new FileHandler(getCustomFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                customLogger.addHandler(fileHandler);
                customLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logChat")) {
            try {
                FileHandler fileHandler = new FileHandler(getChatFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                chatLogger.addHandler(fileHandler);
                chatLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logCommands")) {
            try {
                FileHandler fileHandler = new FileHandler(getCommandFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                commandLogger.addHandler(fileHandler);
                commandLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logJoins")) {
            try {
                FileHandler fileHandler = new FileHandler(getJoinFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                joinLogger.addHandler(fileHandler);
                joinLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logLefts")) {
            try {
                FileHandler fileHandler = new FileHandler(getLeaveFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                leaveLogger.addHandler(fileHandler);
                leaveLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logTeleports")) {
            try {
                FileHandler fileHandler = new FileHandler(getTeleportFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                teleportLogger.addHandler(fileHandler);
                teleportLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (config.getBoolean("logPings")) {
            try {
                FileHandler fileHandler = new FileHandler(getPingFolder() + File.separator + fileName);
                fileHandler.setFormatter(formatter);
                pingLogger.addHandler(fileHandler);
                pingLogger.setUseParentHandlers(false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public File getChatFolder() {
        return chatFolder;
    }

    public File getCommandFolder() {
        return commandFolder;
    }

    public File getCustomFolder() {
        return customFolder;
    }

    public Logger getChatLogger() {
        return chatLogger;
    }

    public Logger getCommandLogger() {
        return commandLogger;
    }

    public Logger getCustomLogger() {
        return customLogger;
    }

    public File getJoinFolder() {
        return joinFolder;
    }

    public File getLeaveFolder() {
        return leaveFolder;
    }

    public File getPingFolder() {
        return pingFolder;
    }

    public File getTeleportFolder() {
        return teleportFolder;
    }

    public Logger getJoinLogger() {
        return joinLogger;
    }

    public Logger getLeaveLogger() {
        return leaveLogger;
    }

    public Logger getPingLogger() {
        return pingLogger;
    }

    public Logger getTeleportLogger() {
        return teleportLogger;
    }
}