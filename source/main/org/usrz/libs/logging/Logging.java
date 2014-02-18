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


/**
 * A class initializing the logging environment.
 *
 * <p>Simply call the {@link #init()} method at the first line of your
 * <code>main(String[] args)</code> method, or just add a line like
 * <code>static { Logging.init(); }</code> on top of your primary class.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Logging {

    private static boolean initialized = false;

    private Logging() {
        throw new IllegalStateException("Do not construct");
    }

    /**
     * Conquer all logging subsystems, and rule their world with an iron fist!
     *
     * <p>This method, ironically enough, will fail badly if the world can
     * not be conquered. Call this <b>first</b> before you do <i>anything</i>
     * else.</p>
     *
     * <p>Called this way, this method will not be verbosely prompting to
     * standard error what's happening.</p>
     */
    public static void init() {
        init(false);
    }

    /**
     * Conquer all logging subsystems, and rule their world with an iron fist!
     *
     * <p>This method, ironically enough, will fail badly if the world can
     * not be conquered. Call this <b>first</b> before you do <i>anything</i>
     * else.</p>
     *
     * @param verbose If <b>true</b> debug output will be written to
     *                {@linkplain System#err standard error}.
     */
    public static void init(boolean verbose) {
        if (initialized) return;

        synchronized (Logging.class) {

            /* Java Logging initialization */
            try {
                JavaLoggingInitializer.init();
                if (verbose) System.err.println("Java Logging initialized");
            } catch (NoClassDefFoundError error) {
                if (verbose) System.err.println("Java Logging not found");
            }

            /* Log4j 1 Logging initialization */
            try {
                Log4j1Initializer.init();
                if (verbose) System.err.println("Log4j v1 Logging initialized");
            } catch (NoClassDefFoundError error) {
                if (verbose) System.err.println("Log4j v1 Logging not found");
            }

            /* Log4j 2 Logging initialization */
            try {
                Log4j2Initializer.init();
                if (verbose) System.err.println("Log4j v2 Logging initialized");
            } catch (NoClassDefFoundError error) {
                if (verbose) System.err.println("Log4j v2 Logging not found");
            }

            /* Commons Logging initialization */
            try {
                CommonsLoggingInitializer.init();
                if (verbose) System.err.println("Commons Logging initialized");
            } catch (NoClassDefFoundError error) {
                if (verbose) System.err.println("Commons Logging not found");
            }

            /* We are initialized */
            initialized = true;

        }
    }

}
