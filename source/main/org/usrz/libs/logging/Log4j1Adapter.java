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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.slf4j.LoggerFactory;

public class Log4j1Adapter extends Logger {

    private final org.slf4j.Logger logger;

    public Log4j1Adapter(Log4j1Bridge bridge, String name) {
        super(name);
        logger = LoggerFactory.getLogger(name);
        repository = bridge;

        super.setLevel(logger.isTraceEnabled() ? Level.TRACE :
                       logger.isDebugEnabled() ? Level.DEBUG :
                       logger.isInfoEnabled()  ? Level.INFO :
                       logger.isWarnEnabled()  ? Level.WARN :
                       logger.isErrorEnabled() ? Level.ERROR :
                       Level.OFF);
    }

    public void setLevel(Level level) {
        /* Do nothing */
    }

    public Level getEffectiveLevel() {
        return getLevel();
    }

    public void callAppenders(LoggingEvent event) {
        final Level level = event.getLevel();
        if (level.equals(Level.OFF)) return;

        final Object messageObject = event.getMessage();
        final String message = messageObject == null ? "Null message" :
                               messageObject instanceof String ? (String) messageObject :
                               messageObject.toString();
        final ThrowableInformation throwableInformation = event.getThrowableInformation();
        final Throwable throwable = throwableInformation == null ? null : throwableInformation.getThrowable();

        if        (level.equals(Level.FATAL)) {
            if (throwable == null) logger.error(message);
            else                   logger.error(message, throwable);
        } else if (level.equals(Level.ERROR)) {
            if (throwable == null) logger.error(message);
            else                   logger.error(message, throwable);
        } else if (level.equals(Level.WARN)) {
            if (throwable == null) logger.warn(message);
            else                   logger.warn(message, throwable);
        } else if (level.equals(Level.INFO)) {
            if (throwable == null) logger.info(message);
            else                   logger.info(message, throwable);
        } else if (level.equals(Level.DEBUG)) {
            if (throwable == null) logger.debug(message);
            else                   logger.debug(message, throwable);
        } else if (level.equals(Level.TRACE)) {
            if (throwable == null) logger.trace(message);
            else                   logger.trace(message, throwable);
        }
    }

}
