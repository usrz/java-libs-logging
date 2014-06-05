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

import org.apache.commons.logging.LogFactory;


/**
 * A class initializing the
 * <a href="http://commons.apache.org/proper/commons-logging/">Apache Commons
 * Logging</a> logging subsystem.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class CommonsLoggingInitializer {

    private static final String SYSTEM_PROPERTY = "org.apache.commons.logging.LogFactory";
    private static boolean initialized = false;

    private CommonsLoggingInitializer() {
        throw new IllegalStateException("Do not construct");
    }

    public static void init() {
        if (initialized) return;

        synchronized (CommonsLoggingInitializer.class) {
            try {
                final String property = System.getProperty(SYSTEM_PROPERTY);
                if ((property != null) && (!CommonsLoggingBridge.class.getName().equals(property)))
                    throw new IllegalStateException("System property \"" + SYSTEM_PROPERTY + "\" set to \"" + property + "\"");
                System.setProperty(SYSTEM_PROPERTY, CommonsLoggingBridge.class.getName());

                final CommonsLoggingBridge bridge = (CommonsLoggingBridge) LogFactory.getFactory();
                final CommonsLoggingAdapter adapter = (CommonsLoggingAdapter) LogFactory.getLog(JavaLoggingInitializer.class);

                if (bridge == null) throw new IllegalStateException("No Commons LogFactory instantiated");
                if (adapter == null) throw new IllegalStateException("No Commons Log instantiated");

            } catch (ClassCastException exception) {
                System.err.println("WARNING: Commons Logging not initialized:" +
                        " bridge=" + LogFactory.getFactory().getClass().getName() +
                        " adapter=" + LogFactory.getLog(JavaLoggingInitializer.class).getClass().getName());
            }

            /* We are initialized */
            initialized = true;
        }
    }
}
