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

import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * A class initializing
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html">Java's Logging API (<i>java.util.logging</i>)</a>.
 * logging subsystem.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class JavaLoggingInitializer {

    private static final String SYSTEM_PROPERTY = "java.util.logging.manager";
    private static boolean initialized = false;

    private JavaLoggingInitializer() {
        throw new IllegalStateException("Do not construct");
    }

    public static void init() {
        if (initialized) return;

        synchronized (JavaLoggingInitializer.class) {
            try {
                final String property = System.getProperty(SYSTEM_PROPERTY);
                if ((property != null) && (!JavaLoggingBridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"" + SYSTEM_PROPERTY + "\" set to \"" + property + "\"");
                System.setProperty(SYSTEM_PROPERTY, JavaLoggingBridge.class.getName());

                final JavaLoggingBridge bridge = (JavaLoggingBridge) LogManager.getLogManager();
                final JavaLoggingAdapter adapter = (JavaLoggingAdapter) Logger.getLogger(JavaLoggingInitializer.class.getName());

                if (bridge == null) throw new IllegalStateException("No Java LogManager instantiated");
                if (adapter == null) throw new IllegalStateException("No Java Logger instantiated");

            } catch (ClassCastException exception) {
                System.err.println("WARNING: Java Logging not initialized:" +
                        " bridge=" + LogManager.getLogManager().getClass().getName() +
                        " adapter=" + Logger.getLogger(JavaLoggingInitializer.class.getName()).getClass().getName());
            }

            /* We are initialized */
            initialized = true;
        }
    }

}
