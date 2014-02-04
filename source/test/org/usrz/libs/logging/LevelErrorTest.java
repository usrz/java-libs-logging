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

import org.apache.log4j.Priority;
import org.testng.annotations.Test;
import org.usrz.libs.testing.AbstractTest;


public class LevelErrorTest extends AbstractTest {

    static { Logging.init(); }

    @Test
    public void testCommonsLogging() {
        final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

        logger.trace("Foobar TRACE");
        AppenderForTests.hasNoLastEvent("at Trace level");
        assertFalse(logger.isTraceEnabled());

        logger.debug("Foobar DEBUG");
        AppenderForTests.hasNoLastEvent("at Debug level");
        assertFalse(logger.isDebugEnabled());

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isInfoEnabled());

        logger.warn("Foobar WARN");
        AppenderForTests.hasNoLastEvent("at Warn level");
        assertFalse(logger.isWarnEnabled());

        logger.error("Foobar ERROR");
        AppenderForTests.hasLastEvent("at Error level");
        assertTrue(logger.isErrorEnabled());

        logger.fatal("Foobar FATAL");
        AppenderForTests.hasLastEvent("at Fatal level");
        assertTrue(logger.isFatalEnabled());

    }

    @Test
    public void testJavaLogging() {
        final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());

        logger.finest("Foobar FINEST");
        AppenderForTests.hasNoLastEvent("at Finest level");
        assertFalse(logger.isLoggable(java.util.logging.Level.FINEST));

        logger.finer("Foobar FINER");
        AppenderForTests.hasNoLastEvent("at Finer level");
        assertFalse(logger.isLoggable(java.util.logging.Level.FINER));

        logger.fine("Foobar FINE");
        AppenderForTests.hasNoLastEvent("at Fine level");
        assertFalse(logger.isLoggable(java.util.logging.Level.FINE));

        logger.config("Foobar CONFIG");
        AppenderForTests.hasNoLastEvent("at Config level");
        assertFalse(logger.isLoggable(java.util.logging.Level.CONFIG));

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isLoggable(java.util.logging.Level.INFO));

        logger.warning("Foobar WARNING");
        AppenderForTests.hasNoLastEvent("at Warning level");
        assertFalse(logger.isLoggable(java.util.logging.Level.WARNING));

        logger.severe("Foobar SEVERE");
        AppenderForTests.hasLastEvent("at Severe level");
        assertTrue(logger.isLoggable(java.util.logging.Level.SEVERE));

    }

    @Test @SuppressWarnings("deprecation")
    public void testLog4j1Logging() {
        final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

        logger.trace("Foobar TRACE");
        AppenderForTests.hasNoLastEvent("at Trace level");
        assertFalse(logger.isTraceEnabled());

        logger.debug("Foobar DEBUG");
        AppenderForTests.hasNoLastEvent("at Debug level");
        assertFalse(logger.isDebugEnabled());

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isInfoEnabled());

        logger.warn("Foobar WARN");
        AppenderForTests.hasNoLastEvent("at Warn level");
        assertFalse(logger.isEnabledFor(Priority.WARN));

        logger.error("Foobar ERROR");
        AppenderForTests.hasLastEvent("at Error level");
        assertTrue(logger.isEnabledFor(Priority.ERROR));

        logger.fatal("Foobar FATAL");
        AppenderForTests.hasLastEvent("at Fatal level");
        assertTrue(logger.isEnabledFor(Priority.FATAL));

    }

    @Test
    public void testLog4j2Logging() {
        final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

        logger.trace("Foobar TRACE");
        AppenderForTests.hasNoLastEvent("at Trace level");
        assertFalse(logger.isTraceEnabled());

        logger.debug("Foobar DEBUG");
        AppenderForTests.hasNoLastEvent("at Debug level");
        assertFalse(logger.isDebugEnabled());

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isInfoEnabled());

        logger.warn("Foobar WARN");
        AppenderForTests.hasNoLastEvent("at Warn level");
        assertFalse(logger.isWarnEnabled());

        logger.error("Foobar ERROR");
        AppenderForTests.hasLastEvent("at Error level");
        assertTrue(logger.isErrorEnabled());

        logger.fatal("Foobar FATAL");
        AppenderForTests.hasLastEvent("at Fatal level");
        assertTrue(logger.isFatalEnabled());

    }

    @Test
    public void testSLF4JLogging() {
        final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

        logger.trace("Foobar TRACE");
        AppenderForTests.hasNoLastEvent("at Trace level");
        assertFalse(logger.isTraceEnabled());

        logger.debug("Foobar DEBUG");
        AppenderForTests.hasNoLastEvent("at Debug level");
        assertFalse(logger.isDebugEnabled());

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isInfoEnabled());

        logger.warn("Foobar WARN");
        AppenderForTests.hasNoLastEvent("at Warn level");
        assertFalse(logger.isWarnEnabled());

        logger.error("Foobar ERROR");
        AppenderForTests.hasLastEvent("at Error level");
        assertTrue(logger.isErrorEnabled());

    }

    @Test @SuppressWarnings("deprecation")
    public void testOurLogLogging() {
        final Log logger = new Log();

        logger.trace("Foobar TRACE");
        AppenderForTests.hasNoLastEvent("at Trace level");
        assertFalse(logger.isTraceEnabled());

        logger.debug("Foobar DEBUG");
        AppenderForTests.hasNoLastEvent("at Debug level");
        assertFalse(logger.isDebugEnabled());

        logger.info("Foobar INFO");
        AppenderForTests.hasNoLastEvent("at Info level");
        assertFalse(logger.isInfoEnabled());

        logger.warn("Foobar WARN");
        AppenderForTests.hasNoLastEvent("at Warn level");
        assertFalse(logger.isWarnEnabled());

        logger.error("Foobar ERROR");
        AppenderForTests.hasLastEvent("at Error level");
        assertTrue(logger.isErrorEnabled());

    }


}
