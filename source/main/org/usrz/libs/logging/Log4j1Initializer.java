/* ========================================================================== *
 * Copyright 2014 USRZ.com and Pier Paolo Fumagalli                           *
 * -------------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *  http://www.apache.org/licenses/LICENSE-2.0                                *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 * ========================================================================== */
package org.usrz.libs.logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * A class initializing the
 * <a href="http://logging.apache.org/log4j/1.2/">Apache Log4j 1.2</a>
 * logging subsystem.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j1Initializer {

    private static boolean initialized = false;

    private Log4j1Initializer() {
        throw new IllegalStateException("Do not construct");
    }

    public static void init() {
        if (initialized) return;

        synchronized (Log4j1Initializer.class) {
            try {
                LogManager.setRepositorySelector(new Log4j1Bridge(), new Object());

                final Log4j1Bridge bridge = (Log4j1Bridge) LogManager.getLoggerRepository();
                final Log4j1Adapter adapter = (Log4j1Adapter) Logger.getLogger(Log4j1Initializer.class);

                if (bridge == null) throw new IllegalStateException("No Log4j v1 LoggerRepository instantiated");
                if (adapter == null) throw new IllegalStateException("No Log4j v1 Logger instantiated");

            } catch (ClassCastException exception ) {
                throw new IllegalStateException("Log4j v1 Logging not initialized:" +
                        " bridge=" + LogManager.getLoggerRepository().getClass().getName() +
                        " adapter=" + Logger.getLogger(Log4j1Initializer.class).getClass().getName());
            }

            /* We are initialized */
            initialized = true;
        }
    }

}
