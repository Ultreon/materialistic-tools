package io.github.ultreon.mods.materialistictools.debug;

import dev.architectury.platform.Platform;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import org.apache.commons.lang3.StringUtils;

public class Debugger {

    public static void log(String message) {
        if (Platform.isDevelopmentEnvironment()) {
            MaterialisticTools.LOGGER.info(message);
        }
    }

    public static void log(Object... args) {
        if (Platform.isDevelopmentEnvironment()) {
            MaterialisticTools.LOGGER.info(StringUtils.join(args, " "));
        }
    }
}
