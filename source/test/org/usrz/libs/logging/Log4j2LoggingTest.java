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

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

public class Log4j2LoggingTest {

    static { Logging.init(); }

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Random random = new Random();

    @Test
    public void testLog4j2LoggingTrace() {
        final String message = "Hello world " + random.nextInt();

        logger.trace(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingTraceThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.trace(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testLog4j2LoggingDebug() {
        final String message = "Hello world " + random.nextInt();

        logger.debug(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.DEBUG)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingDebugThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.debug(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.DEBUG)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testLog4j2LoggingInfo() {
        final String message = "Hello world " + random.nextInt();

        logger.info(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingInfoThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.info(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testLog4j2LoggingWarn() {
        final String message = "Hello world " + random.nextInt();

        logger.warn(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.WARN)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingWarnThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.warn(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.WARN)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testLog4j2LoggingError() {
        final String message = "Hello world " + random.nextInt();

        logger.error(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingErrorThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.error(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testLog4j2LoggingFatal() {
        final String message = "Hello world " + random.nextInt();

        logger.fatal(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testLog4j2LoggingFatalThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.fatal(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

}
