package com.cfil360.advancedkits.managers;

import com.cfil360.advancedkits.AdvancedKits;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Connor on 7/28/2015.
 */
public class SettingsManager {

    private static SettingsManager kits = new SettingsManager("kits");

    public static SettingsManager getKits() {
        return kits;
    }

    private SettingsManager(String fileName) {
        System.out.println(AdvancedKits.getPlugin());

        if (!AdvancedKits.getPlugin().getDataFolder().exists()) AdvancedKits.getPlugin().getDataFolder().mkdir();

        AdvancedKits.getPlugin().saveResource("kits.yml", false);

        file = new File(AdvancedKits.getPlugin().getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    private File file;
    private FileConfiguration config;

    public FileConfiguration getConfig() {
        return config;
    }

    public void set(String path, Object value) {
        config.set(path, value);
        try { config.save(file); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public ConfigurationSection createConfigurationSection(String path) {
        ConfigurationSection cs = config.createSection(path);
        try { config.save(file); }
        catch (Exception e) { e.printStackTrace(); }
        return cs;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) config.get(path);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }
}
