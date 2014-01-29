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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://logging.apache.org/log4j/2.x/">Apache Log4j 2</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j2Adapter extends AbstractLogger {

    private final Logger logger;

    protected Log4j2Adapter(String name) {
        super(name);
        logger = LoggerFactory.getLogger(name);
    }

    protected Log4j2Adapter(String name, MessageFactory factory) {
        super(name, factory);
        logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void log(Marker marker, String fqcn, Level level, Message data, Throwable t) {
        if (level == null) return;
        if (isEnabled(level, marker)) {
            final String message = data.getFormattedMessage();
            switch (level) {
                case TRACE: logger.trace(message, t); break;
                case DEBUG: logger.debug(message, t); break;
                case INFO:  logger.info (message, t); break;
                case WARN:  logger.warn (message, t); break;
                case ERROR: logger.error(message, t); break;
                case FATAL: logger.error(message, t); break;
                default: return;
            }
        }
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