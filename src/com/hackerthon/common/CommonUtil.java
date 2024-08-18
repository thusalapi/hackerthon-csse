package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * Class responsible for loading configuration properties.
 */
public class CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class.getName());
    public static final Properties configProperties = new Properties();

    static {
        try {
            configProperties.load(CommonUtil.class.getResourceAsStream(CommonConstants.CONFIG_FILE_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load configuration properties", e);
        }
    }

}