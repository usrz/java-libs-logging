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

import org.testng.annotations.Test;
import org.usrz.libs.testing.AbstractTest;

import ch.qos.logback.classic.Level;

public class LogMethodsTest extends AbstractTest {

    static { Logging.init(); }

    private static final Log log = new Log();

    @Test
    public void testLogTrace() {
        log.trace("Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(null);

        log.trace("Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(null);

        log.trace("Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(null);

        log.trace("Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(null);

        log.trace("Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(null);
    }

    @Test
    public void testLogTraceThrowable() {
        final RuntimeException exception1 = new IllegalArgumentException("First");
        log.trace(exception1, "Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(exception1);

        final RuntimeException exception2 = new IllegalArgumentException("Second");
        log.trace(exception2, "Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(exception2);

        final RuntimeException exception3 = new IllegalArgumentException("Third");
        log.trace(exception3, "Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(exception3);

        final RuntimeException exception4 = new IllegalArgumentException("Fourth");
        log.trace(exception4, "Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(exception4);

        final RuntimeException exception5 = new IllegalArgumentException("Fifth");
        log.trace(exception5, "Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(exception5);

        final RuntimeException exceptionX = new IllegalArgumentException("Reversed!");
        log.trace("Hello reverse", exceptionX);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.TRACE)
                                       .assertMessage("Hello reverse")
                                       .assertThrowable(exceptionX);

    }

    @Test
    public void testLogDebug() {
        log.debug("Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(null);

        log.debug("Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(null);

        log.debug("Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(null);

        log.debug("Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(null);

        log.debug("Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(null);
    }

    @Test
    public void testLogDebugThrowable() {
        final RuntimeException exception1 = new IllegalArgumentException("First");
        log.debug(exception1, "Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(exception1);

        final RuntimeException exception2 = new IllegalArgumentException("Second");
        log.debug(exception2, "Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(exception2);

        final RuntimeException exception3 = new IllegalArgumentException("Third");
        log.debug(exception3, "Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(exception3);

        final RuntimeException exception4 = new IllegalArgumentException("Fourth");
        log.debug(exception4, "Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(exception4);

        final RuntimeException exception5 = new IllegalArgumentException("Fifth");
        log.debug(exception5, "Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(exception5);

        final RuntimeException exceptionX = new IllegalArgumentException("Reversed!");
        log.debug("Hello reverse", exceptionX);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.DEBUG)
                                       .assertMessage("Hello reverse")
                                       .assertThrowable(exceptionX);

    }

    @Test
    public void testLogInfo() {
        log.info("Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(null);

        log.info("Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(null);

        log.info("Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(null);

        log.info("Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(null);

        log.info("Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(null);
    }

    @Test
    public void testLogInfoThrowable() {
        final RuntimeException exception1 = new IllegalArgumentException("First");
        log.info(exception1, "Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(exception1);

        final RuntimeException exception2 = new IllegalArgumentException("Second");
        log.info(exception2, "Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(exception2);

        final RuntimeException exception3 = new IllegalArgumentException("Third");
        log.info(exception3, "Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(exception3);

        final RuntimeException exception4 = new IllegalArgumentException("Fourth");
        log.info(exception4, "Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(exception4);

        final RuntimeException exception5 = new IllegalArgumentException("Fifth");
        log.info(exception5, "Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(exception5);

        final RuntimeException exceptionX = new IllegalArgumentException("Reversed!");
        log.info("Hello reverse", exceptionX);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.INFO)
                                       .assertMessage("Hello reverse")
                                       .assertThrowable(exceptionX);

    }

    @Test
    public void testLogWarn() {
        log.warn("Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(null);

        log.warn("Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(null);

        log.warn("Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(null);

        log.warn("Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(null);

        log.warn("Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(null);
    }

    @Test
    public void testLogWarnThrowable() {
        final RuntimeException exception1 = new IllegalArgumentException("First");
        log.warn(exception1, "Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(exception1);

        final RuntimeException exception2 = new IllegalArgumentException("Second");
        log.warn(exception2, "Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(exception2);

        final RuntimeException exception3 = new IllegalArgumentException("Third");
        log.warn(exception3, "Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(exception3);

        final RuntimeException exception4 = new IllegalArgumentException("Fourth");
        log.warn(exception4, "Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(exception4);

        final RuntimeException exception5 = new IllegalArgumentException("Fifth");
        log.warn(exception5, "Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(exception5);

        final RuntimeException exceptionX = new IllegalArgumentException("Reversed!");
        log.warn("Hello reverse", exceptionX);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello reverse")
                                       .assertThrowable(exceptionX);

    }

    @Test
    public void testLogError() {
        log.error("Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(null);

        log.error("Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(null);

        log.error("Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(null);

        log.error("Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(null);

        log.error("Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(null);
    }

    @Test
    public void testLogErrorThrowable() {
        final RuntimeException exception1 = new IllegalArgumentException("First");
        log.error(exception1, "Hello, world!");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello, world!")
                                       .assertThrowable(exception1);

        final RuntimeException exception2 = new IllegalArgumentException("Second");
        log.error(exception2, "Hello %d", 1);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1")
                                       .assertThrowable(exception2);

        final RuntimeException exception3 = new IllegalArgumentException("Third");
        log.error(exception3, "Hello %c %c", '1', '2');
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1 2")
                                       .assertThrowable(exception3);

        final RuntimeException exception4 = new IllegalArgumentException("Fourth");
        log.error(exception4, "Hello %s %s %s", "one", "two", "three");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello one two three")
                                       .assertThrowable(exception4);

        final RuntimeException exception5 = new IllegalArgumentException("Fifth");
        log.error(exception5, "Hello %d %c %s %s", 1, '2', "three", "and more...");
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello 1 2 three and more...")
                                       .assertThrowable(exception5);

        final RuntimeException exceptionX = new IllegalArgumentException("Reversed!");
        log.error("Hello reverse", exceptionX);
        AppenderForTests.hasLastEvent().assertCaller(this)
                                       .assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello reverse")
                                       .assertThrowable(exceptionX);

    }

}
