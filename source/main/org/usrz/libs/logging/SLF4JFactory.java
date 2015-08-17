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

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A simple factory for SLF4J {@link LocationAwareLogger} instances.
 *
 * <p>This class will wrap a {@link Logger} if the underlying provider does
 * not implement the {@link LocationAwareLogger} interface.</p>
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public class SLF4JFactory {

    private static final ConcurrentHashMap<String, LocationAwareLogger> CACHE = new ConcurrentHashMap<>();

    /**
     * Return the root {@link LocationAwareLogger}.
     */
    protected static final LocationAwareLogger getLogger() {
        return getLogger(ROOT_LOGGER_NAME);
    }

    /**
     * Return the {@link LocationAwareLogger} for the specified {@link Class}.
     */
    protected static final LocationAwareLogger getLogger(Class<?> clazz) {
        return getLogger(clazz != null ? clazz.getName() : ROOT_LOGGER_NAME);
    }

    /**
     * Return the {@link LocationAwareLogger} with the specified name.
     */
    protected static final LocationAwareLogger getLogger(String name) {
        if (name == null) name = ROOT_LOGGER_NAME;

        final LocationAwareLogger cached = CACHE.get(name);
        if (cached != null) return cached;

        final LocationAwareLogger created = asLocationAwareLogger(LoggerFactory.getLogger(name));
        final LocationAwareLogger previous = CACHE.putIfAbsent(name, created);

        return previous == null ? created : previous;
    }

    /**
     * Cast (hopefully) or wrap (bad) the specified {@link Logger}
     * into a {@link LocationAwareLogger}.
     */
    protected static LocationAwareLogger asLocationAwareLogger(final Logger logger) {

        /* Cast ? */
        if (logger instanceof LocationAwareLogger) return (LocationAwareLogger) logger;

        /* Wrap ! */
        logger.warn("Logger {} is not a LocationAwareLogger", logger.getClass().getName());
        return new LocationAwareLogger() {

            @Override
            public void log(Marker marker, String fqcn, int level, String message, Object[] argArray, Throwable t) {
                if      (level <= TRACE_INT) { logger.trace(marker, message, argArray); logger.trace(null, t); }
                else if (level <= DEBUG_INT) { logger.debug(marker, message, argArray); logger.debug(null, t); }
                else if (level <= INFO_INT)  { logger.info (marker, message, argArray); logger.info (null, t); }
                else if (level <= WARN_INT)  { logger.warn (marker, message, argArray); logger.warn (null, t); }
                else                         { logger.error(marker, message, argArray); logger.error(null, t); }
            }

            /* ============================================================== */
            /* From here onwards, it's just delegation calls                  */
            /* ============================================================== */

            @Override
            public String getName() {
                return logger.getName();
            }

            @Override
            public boolean isTraceEnabled() {
                return logger.isTraceEnabled();
            }

            @Override
            public void trace(String msg) {
                logger.trace(msg);
            }

            @Override
            public void trace(String format, Object arg) {
                logger.trace(format, arg);
            }

            @Override
            public void trace(String format, Object arg1, Object arg2) {
                logger.trace(format, arg1, arg2);
            }

            @Override
            public void trace(String format, Object... arguments) {
                logger.trace(format, arguments);
            }

            @Override
            public void trace(String msg, Throwable t) {
                logger.trace(msg, t);
            }

            @Override
            public boolean isTraceEnabled(Marker marker) {
                return logger.isTraceEnabled(marker);
            }

            @Override
            public void trace(Marker marker, String msg) {
                logger.trace(marker, msg);
            }

            @Override
            public void trace(Marker marker, String format, Object arg) {
                logger.trace(marker, format, arg);
            }

            @Override
            public void trace(Marker marker, String format, Object arg1,
                    Object arg2) {
                logger.trace(marker, format, arg1, arg2);
            }

            @Override
            public void trace(Marker marker, String format, Object... argArray) {
                logger.trace(marker, format, argArray);
            }

            @Override
            public void trace(Marker marker, String msg, Throwable t) {
                logger.trace(marker, msg, t);
            }

            @Override
            public boolean isDebugEnabled() {
                return logger.isDebugEnabled();
            }

            @Override
            public void debug(String msg) {
                logger.debug(msg);
            }

            @Override
            public void debug(String format, Object arg) {
                logger.debug(format, arg);
            }

            @Override
            public void debug(String format, Object arg1, Object arg2) {
                logger.debug(format, arg1, arg2);
            }

            @Override
            public void debug(String format, Object... arguments) {
                logger.debug(format, arguments);
            }

            @Override
            public void debug(String msg, Throwable t) {
                logger.debug(msg, t);
            }

            @Override
            public boolean isDebugEnabled(Marker marker) {
                return logger.isDebugEnabled(marker);
            }

            @Override
            public void debug(Marker marker, String msg) {
                logger.debug(marker, msg);
            }

            @Override
            public void debug(Marker marker, String format, Object arg) {
                logger.debug(marker, format, arg);
            }

            @Override
            public void debug(Marker marker, String format, Object arg1, Object arg2) {
                logger.debug(marker, format, arg1, arg2);
            }

            @Override
            public void debug(Marker marker, String format, Object... arguments) {
                logger.debug(marker, format, arguments);
            }

            @Override
            public void debug(Marker marker, String msg, Throwable t) {
                logger.debug(marker, msg, t);
            }

            @Override
            public boolean isInfoEnabled() {
                return logger.isInfoEnabled();
            }

            @Override
            public void info(String msg) {
                logger.info(msg);
            }

            @Override
            public void info(String format, Object arg) {
                logger.info(format, arg);
            }

            @Override
            public void info(String format, Object arg1, Object arg2) {
                logger.info(format, arg1, arg2);
            }

            @Override
            public void info(String format, Object... arguments) {
                logger.info(format, arguments);
            }

            @Override
            public void info(String msg, Throwable t) {
                logger.info(msg, t);
            }

            @Override
            public boolean isInfoEnabled(Marker marker) {
                return logger.isInfoEnabled(marker);
            }

            @Override
            public void info(Marker marker, String msg) {
                logger.info(marker, msg);
            }

            @Override
            public void info(Marker marker, String format, Object arg) {
                logger.info(marker, format, arg);
            }

            @Override
            public void info(Marker marker, String format, Object arg1, Object arg2) {
                logger.info(marker, format, arg1, arg2);
            }

            @Override
            public void info(Marker marker, String format, Object... arguments) {
                logger.info(marker, format, arguments);
            }

            @Override
            public void info(Marker marker, String msg, Throwable t) {
                logger.info(marker, msg, t);
            }

            @Override
            public boolean isWarnEnabled() {
                return logger.isWarnEnabled();
            }

            @Override
            public void warn(String msg) {
                logger.warn(msg);
            }

            @Override
            public void warn(String format, Object arg) {
                logger.warn(format, arg);
            }

            @Override
            public void warn(String format, Object... arguments) {
                logger.warn(format, arguments);
            }

            @Override
            public void warn(String format, Object arg1, Object arg2) {
                logger.warn(format, arg1, arg2);
            }

            @Override
            public void warn(String msg, Throwable t) {
                logger.warn(msg, t);
            }

            @Override
            public boolean isWarnEnabled(Marker marker) {
                return logger.isWarnEnabled(marker);
            }

            @Override
            public void warn(Marker marker, String msg) {
                logger.warn(marker, msg);
            }

            @Override
            public void warn(Marker marker, String format, Object arg) {
                logger.warn(marker, format, arg);
            }

            @Override
            public void warn(Marker marker, String format, Object arg1, Object arg2) {
                logger.warn(marker, format, arg1, arg2);
            }

            @Override
            public void warn(Marker marker, String format, Object... arguments) {
                logger.warn(marker, format, arguments);
            }

            @Override
            public void warn(Marker marker, String msg, Throwable t) {
                logger.warn(marker, msg, t);
            }

            @Override
            public boolean isErrorEnabled() {
                return logger.isErrorEnabled();
            }

            @Override
            public void error(String msg) {
                logger.error(msg);
            }

            @Override
            public void error(String format, Object arg) {
                logger.error(format, arg);
            }

            @Override
            public void error(String format, Object arg1, Object arg2) {
                logger.error(format, arg1, arg2);
            }

            @Override
            public void error(String format, Object... arguments) {
                logger.error(format, arguments);
            }

            @Override
            public void error(String msg, Throwable t) {
                logger.error(msg, t);
            }

            @Override
            public boolean isErrorEnabled(Marker marker) {
                return logger.isErrorEnabled(marker);
            }

            @Override
            public void error(Marker marker, String msg) {
                logger.error(marker, msg);
            }

            @Override
            public void error(Marker marker, String format, Object arg) {
                logger.error(marker, format, arg);
            }

            @Override
            public void error(Marker marker, String format, Object arg1, Object arg2) {
                logger.error(marker, format, arg1, arg2);
            }

            @Override
            public void error(Marker marker, String format, Object... arguments) {
                logger.error(marker, format, arguments);
            }

            @Override
            public void error(Marker marker, String msg, Throwable t) {
                logger.error(marker, msg, t);
            }
        };
    }
}
