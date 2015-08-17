package org.usrz.libs.logging;

import static java.lang.Integer.MIN_VALUE;
import static org.slf4j.spi.LocationAwareLogger.DEBUG_INT;
import static org.slf4j.spi.LocationAwareLogger.ERROR_INT;
import static org.slf4j.spi.LocationAwareLogger.INFO_INT;
import static org.slf4j.spi.LocationAwareLogger.TRACE_INT;
import static org.slf4j.spi.LocationAwareLogger.WARN_INT;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.slf4j.spi.LocationAwareLogger;

public class JavaLoggingHandler extends Handler {

    private static final int SEVERE  = Level.SEVERE.intValue();
    private static final int WARNING = Level.WARNING.intValue();
    private static final int CONFIG  = Level.CONFIG.intValue();
    private static final int FINE    = Level.FINE.intValue();
    private static final int FINEST  = Level.FINEST.intValue();

    private static final String FQCN = Logger.class.getName();

    /* ====================================================================== */

    private final Formatter formatter = new Formatter() {

        @Override
        public String format(LogRecord record) {
            return formatMessage(record);
        }

    };

    /* ====================================================================== */

    protected JavaLoggingHandler() {
        // Nothing to do
    }

    @Override
    public void publish(LogRecord record) {
        final String name = record.getLoggerName();
        final LocationAwareLogger logger = SLF4JFactory.getLogger(name);

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

    @Override
    public void flush() {
        // Auto flushed!
    }

    @Override
    public void close() {
        // Never close!
    }

}
