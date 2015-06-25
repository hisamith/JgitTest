package org.buddycode.jgit.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by samith on 6/25/15.
 */
public class Configs {


    private static final String CONFIG_PROPERTIES_FILE = "config.properties";
    private static Configuration config;

    static {
        try {
            config = new PropertiesConfiguration(CONFIG_PROPERTIES_FILE);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String getProerty(String key) {
        return (String) config.getProperty(key);
    }
}
