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


public final class Logging {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;

        synchronized (Logging.class) {

            /* Java Logging initialization */
            try {
                final String property = System.getProperty("java.util.logging.manager");
                if ((property != null) && (!JavaLoggingBridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"java.util.logging.manager\" set to \"" + property + "\"");
                System.setProperty("java.util.logging.manager", JavaLoggingBridge.class.getName());

                final JavaLoggingBridge bridge = (JavaLoggingBridge) java.util.logging.LogManager.getLogManager();
                final JavaLoggingAdapter adapter = (JavaLoggingAdapter) java.util.logging.Logger.getLogger(Logging.class.getName());

                if (bridge == null) throw new IllegalStateException("No Java LogManager instantiated");
                if (adapter == null) throw new IllegalStateException("No Java Logger instantiated");

                adapter.fine("Java Logging initialized");
            } catch (ClassCastException exception) {
                throw new IllegalStateException("Java Logging not initialized:" +
                        " bridge=" + java.util.logging.LogManager.getLogManager().getClass().getName() +
                        " adapter=" + java.util.logging.Logger.getLogger(Logging.class.getName()).getClass().getName());
            }

            /* Log4j 1 Logging initialization */
            try {
                org.apache.log4j.LogManager.setRepositorySelector(new Log4j1Bridge(), new Object());

                final Log4j1Bridge bridge = (Log4j1Bridge) org.apache.log4j.LogManager.getLoggerRepository();
                final Log4j1Adapter adapter = (Log4j1Adapter) org.apache.log4j.Logger.getLogger(Logging.class);

                if (bridge == null) throw new IllegalStateException("No Log4j v1 LoggerRepository instantiated");
                if (adapter == null) throw new IllegalStateException("No Log4j v1 Logger instantiated");

                adapter.debug("Log4j v1 Logging initialized");
            } catch (ClassCastException exception) {
                throw new IllegalStateException("Log4j v1 Logging not initialized:" +
                        " bridge=" + org.apache.log4j.LogManager.getLoggerRepository().getClass().getName() +
                        " adapter=" + org.apache.log4j.Logger.getLogger(Logging.class).getClass().getName());
            }

            /* Log4j 2 Logging initialization */
            try {
                final String property = System.getProperty("log4j2.loggerContextFactory");
                if ((property != null) && (!Log4j2Bridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"log4j2.LoggerContextFactory\" set to \"" + property + "\"");
                System.setProperty("log4j2.loggerContextFactory", Log4j2Bridge.class.getName());

                final Log4j2Bridge bridge = (Log4j2Bridge) org.apache.logging.log4j.LogManager.getContext();
                final Log4j2Adapter adapter = (Log4j2Adapter) org.apache.logging.log4j.LogManager.getLogger(Logging.class);

                if (bridge == null) throw new IllegalStateException("No Log4j v2 LoggerRepository instantiated");
                if (adapter == null) throw new IllegalStateException("No Log4j v2 Logger instantiated");

                adapter.debug("Log4j v2 Logging initialized");
            } catch (ClassCastException exception) {
                throw new IllegalStateException("Log4j v2 Logging not initialized:" +
                        " bridge=" + org.apache.logging.log4j.LogManager.getContext().getClass().getName() +
                        " adapter=" + org.apache.logging.log4j.LogManager.getLogger(Logging.class).getClass().getName());
            }

            /* Commons Logging initialization */
            try {
                final String property = System.getProperty("org.apache.commons.logging.LogFactory");
                if ((property != null) && (!CommonsLoggingBridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"org.apache.commons.logging.LogFactory\" set to \"" + property + "\"");
                System.setProperty("org.apache.commons.logging.LogFactory", CommonsLoggingBridge.class.getName());

                final CommonsLoggingBridge bridge = (CommonsLoggingBridge) org.apache.commons.logging.LogFactory.getFactory();
                final CommonsLoggingAdapter adapter = (CommonsLoggingAdapter) org.apache.commons.logging.LogFactory.getLog(Logging.class);

                if (bridge == null) throw new IllegalStateException("No Commons LogFactory instantiated");
                if (adapter == null) throw new IllegalStateException("No Commons Log instantiated");

                adapter.debug("Commons Logging initialized");
            } catch (ClassCastException exception) {
                throw new IllegalStateException("Commons Logging not initialized:" +
                        " bridge=" + org.apache.commons.logging.LogFactory.getFactory().getClass().getName() +
                        " adapter=" + org.apache.commons.logging.LogFactory.getLog(Logging.class).getClass().getName());
            }

            /* We are initialized */
            initialized = true;
        }
    }

}
