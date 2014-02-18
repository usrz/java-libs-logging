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

import static java.lang.Integer.MIN_VALUE;
import static org.apache.log4j.Level.DEBUG;
import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.FATAL;
import static org.apache.log4j.Level.INFO;
import static org.apache.log4j.Level.TRACE;
import static org.apache.log4j.Level.WARN;
import static org.slf4j.spi.LocationAwareLogger.DEBUG_INT;
import static org.slf4j.spi.LocationAwareLogger.ERROR_INT;
import static org.slf4j.spi.LocationAwareLogger.INFO_INT;
import static org.slf4j.spi.LocationAwareLogger.TRACE_INT;
import static org.slf4j.spi.LocationAwareLogger.WARN_INT;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://logging.apache.org/log4j/1.2/">Apache Log4j 1.2</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j1Adapter extends Logger {

    private static final String FQCN = Logger.class.getName();

    private final LocationAwareLogger logger;

    protected Log4j1Adapter(Log4j1Bridge bridge, String name) {
        super(name);
        logger = SLF4JFactory.getLogger(name);
        repository = bridge;

        super.setLevel(logger.isTraceEnabled() ? Level.TRACE :
                       logger.isDebugEnabled() ? Level.DEBUG :
                       logger.isInfoEnabled()  ? Level.INFO :
                       logger.isWarnEnabled()  ? Level.WARN :
                       logger.isErrorEnabled() ? Level.ERROR :
                       Level.OFF);
    }

    @Override
    public void setLevel(Level level) {
        /* Do nothing */
    }

    @Override
    public Level getEffectiveLevel() {
        return getLevel();
    }

    @Override
    public void callAppenders(LoggingEvent event) {
        final Level level = event.getLevel();
        if (level.equals(Level.OFF)) return;

        final int slf4jLevel = level.equals(FATAL) ? logger.isErrorEnabled() ? ERROR_INT : MIN_VALUE :
                               level.equals(ERROR) ? logger.isErrorEnabled() ? ERROR_INT : MIN_VALUE :
                               level.equals(WARN)  ? logger.isWarnEnabled()  ? WARN_INT  : MIN_VALUE :
                               level.equals(INFO)  ? logger.isInfoEnabled()  ? INFO_INT  : MIN_VALUE :
                               level.equals(DEBUG) ? logger.isDebugEnabled() ? DEBUG_INT : MIN_VALUE :
                               level.equals(TRACE) ? logger.isTraceEnabled() ? TRACE_INT : MIN_VALUE :
                               MIN_VALUE;

        if (slf4jLevel == MIN_VALUE) return;

        final Object messageObject = event.getMessage();
        final String message = messageObject == null ? "Null message" :
                               messageObject instanceof String ? (String) messageObject :
                               messageObject.toString();
        final ThrowableInformation throwableInformation = event.getThrowableInformation();
        final Throwable throwable = throwableInformation == null ? null : throwableInformation.getThrowable();

        logger.log(null, FQCN, slf4jLevel, message, null, throwable);

    }

}
