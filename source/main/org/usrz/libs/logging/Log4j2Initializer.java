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

import org.apache.logging.log4j.LogManager;

/**
 * A class initializing the
 * <a href="http://logging.apache.org/log4j/2.x/">Apache Log4j 2</a>.
 * logging subsystem.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j2Initializer {

    private static final String SYSTEM_PROPERTY = "log4j2.loggerContextFactory";
    private static boolean initialized = false;

    private Log4j2Initializer() {
        throw new IllegalStateException("Do not construct");
    }

    public static void init() {
        if (initialized) return;

        synchronized (Log4j2Initializer.class) {
            try {
                final String property = System.getProperty(SYSTEM_PROPERTY);
                if ((property != null) && (!Log4j2Bridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"" + SYSTEM_PROPERTY + "\" set to \"" + property + "\"");
                System.setProperty(SYSTEM_PROPERTY, Log4j2Bridge.class.getName());

                final Log4j2Bridge bridge = (Log4j2Bridge) LogManager.getContext();
                final Log4j2Adapter adapter = (Log4j2Adapter) LogManager.getLogger(Log4j2Initializer.class);

                if (bridge == null) throw new IllegalStateException("No Log4j v2 LoggerRepository instantiated");
                if (adapter == null) throw new IllegalStateException("No Log4j v2 Logger instantiated");

            } catch (ClassCastException exception) {
                throw new IllegalStateException("Log4j v2 Logging not initialized:" +
                        " bridge=" + LogManager.getContext().getClass().getName() +
                        " adapter=" + LogManager.getLogger(Log4j2Initializer.class).getClass().getName());
            }

            /* We are initialized */
            initialized = true;
        }
    }

}
