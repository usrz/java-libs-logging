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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

public class Log4j1LoggingTest extends Assert {

    static { Logging.init(true); }

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Random random = new Random();

    @Test
    public void testLog4j1LoggingTrace() {
        final String message = "Hello world " + random.nextInt();

        logger.trace(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingTraceThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.trace(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingDebug() {
        final String message = "Hello world " + random.nextInt();

        logger.debug(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingDebugThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.debug(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingInfo() {
        final String message = "Hello world " + random.nextInt();

        logger.info(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingInfoThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.info(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingWarn() {
        final String message = "Hello world " + random.nextInt();

        logger.warn(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingWarnThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.warn(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingError() {
        final String message = "Hello world " + random.nextInt();

        logger.error(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingErrorThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.error(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingFatal() {
        final String message = "Hello world " + random.nextInt();

        logger.fatal(message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage(message)
                                       .assertThrowable(null)
                                       .assertCaller(this);
    }

    @Test
    public void testLog4j1LoggingFatalThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.fatal(message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage(message)
                                       .assertThrowable(throwable)
                                       .assertCaller(this);
    }

}
