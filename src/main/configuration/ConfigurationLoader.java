package main.configuration;

import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {
    private final Properties properties = new Properties();
    String[] regularShift = {};
    String[] nightTimeShift = {};
    String[] midnightShift = {};

    public void loadConfiguration() throws Exception {
        String propertiesFilePath = "src/main/resources/configuration.properties";
        try (FileInputStream input = new FileInputStream(propertiesFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Could not read file: " + propertiesFilePath);
        }

        regularShift = properties.getProperty("shift.regular").split(" ");
        nightTimeShift = properties.getProperty("shift.nighttime").split(" ");
        midnightShift = properties.getProperty("shift.midnight").split(" ");

        checkConfigurationValidity();
    }

    private void checkConfigurationValidity() throws Exception {
        if (regularShift.length + nightTimeShift.length + midnightShift.length != 24) {
            throw new Exception("The shifts are not configured properly, make sure to always put all hours from 0 to 23 in the configuration file.");
        }
    }

    public String[] getRegularShift() {
        return regularShift;
    }

    public String[] getNightTimeShift() {
        return nightTimeShift;
    }

    public String[] getMidnightShift() {
        return midnightShift;
    }
}
