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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;

public class SLF4JTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testSLF4JLoggingError() {
        logger.error("Hello world {}", 1);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.ERROR)
                                       .assertMessage("Hello world 1")
                                       .assertThrowable(null);
    }

    @Test
    public void testSLF4JLoggingErrorThrowable() {
        final Throwable throwable = new Throwable("This is a throwable 1");
        logger.error("Hello world", throwable);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.ERROR)
                                               .assertMessage("Hello world")
                                               .assertThrowable(throwable);
    }

    @Test
    public void testSLF4JLoggingWarn() {
        logger.warn("Hello world {}", 2);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                       .assertLevel(Level.WARN)
                                       .assertMessage("Hello world 2")
                                       .assertThrowable(null);
    }

    @Test
    public void testSLF4JLoggingWarnThrowable() {
        final Throwable throwable = new Throwable("This is a throwable 2");
        logger.warn("Hello world", throwable);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.WARN)
                                               .assertMessage("Hello world")
                                               .assertThrowable(throwable);
    }

    @Test
    public void testSLF4JLoggingInfo() {
        logger.info("Hello world {}", 3);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.INFO)
                                               .assertMessage("Hello world 3")
                                               .assertThrowable(null);
    }

    @Test
    public void testSLF4JLoggingInfoThrowable() {
        final Throwable throwable = new Throwable("This is a throwable 3");
        logger.info("Hello world", throwable);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.INFO)
                                               .assertMessage("Hello world")
                                               .assertThrowable(throwable);
    }

    @Test
    public void testSLF4JLoggingDebug() {
        logger.debug("Hello world {}", 4);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.DEBUG)
                                               .assertMessage("Hello world 4")
                                               .assertThrowable(null);
    }

    @Test
    public void testSLF4JLoggingDebugThrowable() {
        final Throwable throwable = new Throwable("This is a throwable 4");
        logger.debug("Hello world", throwable);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.DEBUG)
                                               .assertMessage("Hello world")
                                               .assertThrowable(throwable);
    }

    @Test
    public void testSLF4JLoggingTraceError() {
        logger.trace("Hello world {}", 5);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.TRACE)
                                               .assertMessage("Hello world 5")
                                               .assertThrowable(null);
    }

    @Test
    public void testSLF4JLoggingTraceThrowable() {
        final Throwable throwable = new Throwable("This is a throwable 5");
        logger.trace("Hello world", throwable);
        AppenderForTests.hasLastEvent().assertClass(this.getClass())
                                               .assertLevel(Level.TRACE)
                                               .assertMessage("Hello world")
                                               .assertThrowable(throwable);
    }

}
