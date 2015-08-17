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
import static org.slf4j.spi.LocationAwareLogger.DEBUG_INT;
import static org.slf4j.spi.LocationAwareLogger.ERROR_INT;
import static org.slf4j.spi.LocationAwareLogger.INFO_INT;
import static org.slf4j.spi.LocationAwareLogger.TRACE_INT;
import static org.slf4j.spi.LocationAwareLogger.WARN_INT;

import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.slf4j.spi.LocationAwareLogger;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html">Java's Logging API (<i>java.util.logging</i>)</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class JavaLoggingAdapter extends Logger {

    private static final int OFF     = Level.OFF.intValue();
    private static final int SEVERE  = Level.SEVERE.intValue();
    private static final int WARNING = Level.WARNING.intValue();
    private static final int CONFIG  = Level.CONFIG.intValue();
    private static final int FINE    = Level.FINE.intValue();
    private static final int FINEST  = Level.FINEST.intValue();

    private static final String FQCN = Logger.class.getName();

    /* ====================================================================== */

    private final LocationAwareLogger logger;
    private int levelValue;

    private final Formatter formatter = new Formatter() {

        @Override
        public String format(LogRecord record) {
            return formatMessage(record);
        }

    };

    /* ====================================================================== */

    protected JavaLoggingAdapter() {
        this(Logger.GLOBAL_LOGGER_NAME);
    }

    protected JavaLoggingAdapter(String name) {
        super(name, null);
        logger = SLF4JFactory.getLogger(name);

        super.setLevel(logger.isTraceEnabled() ? Level.FINEST :
                       logger.isDebugEnabled() ? Level.FINE :
                       logger.isInfoEnabled()  ? Level.CONFIG :
                       logger.isWarnEnabled()  ? Level.WARNING :
                       logger.isErrorEnabled() ? Level.SEVERE :
                       Level.OFF);

        levelValue = getLevel().intValue();
    }

    /* ====================================================================== */

    @Override
    public void log(LogRecord record) {
        if (record.getLevel().intValue() < levelValue || levelValue == OFF) {
            return;
        }
        final Filter filter = super.getFilter();
        if (filter != null && !filter.isLoggable(record)) {
            return;
        }

        final int level = record.getLevel().intValue();

        final int slf4jLevel = level >= SEVERE  ? logger.isErrorEnabled() ? ERROR_INT : MIN_VALUE :
                               level >= WARNING ? logger.isWarnEnabled()  ? WARN_INT  : MIN_VALUE :
                               level >= CONFIG  ? logger.isInfoEnabled()  ? INFO_INT  : MIN_VALUE :
                               level >= FINE    ? logger.isDebugEnabled() ? DEBUG_INT : MIN_VALUE :
                               level >= FINEST  ? logger.isTraceEnabled() ? TRACE_INT : MIN_VALUE :
                               MIN_VALUE;

        if (slf4jLevel != MIN_VALUE) {
            final String message = formatter.formatMessage(record);
            final Throwable throwable = record.getThrown();
            logger.log(null, FQCN, slf4jLevel, message, null, throwable);
        }
    }

    /* ====================================================================== */

    @Override
    public void setLevel(Level newLevel) {
        levelValue = newLevel.intValue();
    }

    @Override
    public void addHandler(Handler handler) {
        // throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    @Override
    public void removeHandler(Handler handler) {
        // throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

}
