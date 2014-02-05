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
import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.FATAL;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.TRACE;
import static org.apache.logging.log4j.Level.WARN;
import static org.slf4j.spi.LocationAwareLogger.DEBUG_INT;
import static org.slf4j.spi.LocationAwareLogger.ERROR_INT;
import static org.slf4j.spi.LocationAwareLogger.INFO_INT;
import static org.slf4j.spi.LocationAwareLogger.TRACE_INT;
import static org.slf4j.spi.LocationAwareLogger.WARN_INT;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://logging.apache.org/log4j/2.x/">Apache Log4j 2</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j2Adapter extends AbstractLogger {

    private final LocationAwareLogger logger;

    protected Log4j2Adapter(String name) {
        super(name);
        logger = (LocationAwareLogger) LoggerFactory.getLogger(name);
    }

    protected Log4j2Adapter(String name, MessageFactory factory) {
        super(name, factory);
        logger = (LocationAwareLogger) LoggerFactory.getLogger(name);
    }

    @Override
    public void log(Marker marker, String fqcn, Level level, Message data, Throwable throwable) {
        if (level == null) return;

        final org.slf4j.Marker slf4jMarker = convert(marker);

        final int slf4jLevel = level.equals(FATAL) ? logger.isErrorEnabled(slf4jMarker) ? ERROR_INT : MIN_VALUE :
                               level.equals(ERROR) ? logger.isErrorEnabled(slf4jMarker) ? ERROR_INT : MIN_VALUE :
                               level.equals(WARN)  ? logger.isWarnEnabled(slf4jMarker)  ? WARN_INT  : MIN_VALUE :
                               level.equals(INFO)  ? logger.isInfoEnabled(slf4jMarker)  ? INFO_INT  : MIN_VALUE :
                               level.equals(DEBUG) ? logger.isDebugEnabled(slf4jMarker) ? DEBUG_INT : MIN_VALUE :
                               level.equals(TRACE) ? logger.isTraceEnabled(slf4jMarker) ? TRACE_INT : MIN_VALUE :
                               MIN_VALUE;

        if (slf4jLevel == MIN_VALUE) return;

        final String message = data.getFormattedMessage();
        logger.log(convert(marker), fqcn, slf4jLevel, message, null, throwable);
    }

    @Override
    public boolean isEnabled(final Level level, Marker marker) {
        if (level == null) return false;
        switch (level) {
            case TRACE: return logger.isTraceEnabled(convert(marker));
            case DEBUG: return logger.isDebugEnabled(convert(marker));
            case INFO:  return logger.isInfoEnabled(convert(marker));
            case WARN:  return logger.isWarnEnabled(convert(marker));
            case ERROR: return logger.isErrorEnabled(convert(marker));
            case FATAL: return logger.isErrorEnabled(convert(marker));
            default: return false;
        }
    }

    private org.slf4j.Marker convert(Marker marker) {
        if (marker == null) return null;
        return MarkerFactory.getMarker(marker.getName());
    }

    /* ====================================================================== */
    /* Utterly useless methods                                                */
    /* ====================================================================== */

    @Override
    protected boolean isEnabled(Level level, Marker marker, Message data, Throwable t) {
        return isEnabled(level, marker);
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, Object data, Throwable t) {
        return isEnabled(level, marker);
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data) {
        return isEnabled(level, marker);
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data, Object... p1) {
        return isEnabled(level, marker);
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data, Throwable t) {
        return isEnabled(level, marker);
    }
}