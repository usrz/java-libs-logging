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

import static java.util.logging.Level.CONFIG;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.Random;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

public class JavaLoggingTest {

    static { Logging.init(); }

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Random random = new Random();

    @Test
    public void testJavaLoggingFinest() {
        final String message = "Hello world " + random.nextInt();

        logger.log(FINEST, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingFinestThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(FINEST, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingFiner() {
        final String message = "Hello world " + random.nextInt();

        logger.log(FINER, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingFinerThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(FINER, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.TRACE)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingFine() {
        final String message = "Hello world " + random.nextInt();

        logger.log(FINE, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.DEBUG)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingFineThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(FINE, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.DEBUG)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingConfig() {
        final String message = "Hello world " + random.nextInt();

        logger.log(CONFIG, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingConfigThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(CONFIG, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingInfo() {
        final String message = "Hello world " + random.nextInt();

        logger.log(INFO, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingInfoThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(INFO, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.INFO)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingWarning() {
        final String message = "Hello world " + random.nextInt();

        logger.log(WARNING, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.WARN)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingWarningThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(WARNING, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.WARN)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

    @Test
    public void testJavaLoggingSevere() {
        final String message = "Hello world " + random.nextInt();

        logger.log(SEVERE, message);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(null);
    }

    @Test
    public void testJavaLoggingSevereThrowable() {
        final String message = "Hello world " + random.nextInt();
        final Throwable throwable = new Throwable("This is a throwable 2");

        logger.log(SEVERE, message, throwable);

        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                   .assertLevel(Level.ERROR)
                                   .assertMessage(message)
                                   .assertThrowable(throwable);
    }

}
