package com.cfil360.uhc.Util;

import com.cfil360.uhc.UHC;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Set;

/**
 * Created by Connor on 4/26/2015.
 */
public class SettingsManager {

    private Plugin plugin;

    private static final SettingsManager
            configuration = new SettingsManager("config"),
            arenas = new SettingsManager("arenas"),
            signs = new SettingsManager("signs");

    public static SettingsManager getConfig() {
        return configuration;
    }

    public static SettingsManager getArenas() {
        return arenas;
    }

    public static SettingsManager getSigns() {
        return signs;
    }

    private File file;
    private FileConfiguration config;

    private SettingsManager(String fileName) {
        plugin = UHC.getPlugin();

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) config.get(path);
    }

    public FileConfiguration getConfigFile() {
        return config;
    }

    public Set<String> getKeys() {
        return config.getKeys(false);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public ConfigurationSection createSection(String path) {
        ConfigurationSection section = config.createSection(path);
        save();
        return section;
    }

    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
