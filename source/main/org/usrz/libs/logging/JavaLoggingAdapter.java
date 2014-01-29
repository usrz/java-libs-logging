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

import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

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
//  private static final int INFO    = Level.INFO.intValue();
    private static final int CONFIG  = Level.CONFIG.intValue();
    private static final int FINE    = Level.FINE.intValue();
//  private static final int FINER   = Level.FINER.intValue();
    private static final int FINEST  = Level.FINEST.intValue();

    /* ====================================================================== */

    private final org.slf4j.Logger logger;
    private final int levelValue;

    private final Formatter formatter = new Formatter() {

        @Override
        public String format(LogRecord record) {
            return formatMessage(record);
        }

    };

    /* ====================================================================== */

    protected JavaLoggingAdapter(String name) {
        super(name, null);
        logger = LoggerFactory.getLogger(name);

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

        Logger logger = this;
        while (logger != null) {
            final int level = record.getLevel().intValue();
            final String message = formatter.formatMessage(record);
            final Throwable throwable = record.getThrown();

            if        (level >= SEVERE) {
                if (throwable == null) this.logger.error(message);
                else                   this.logger.error(message, throwable);
            } else if (level >= WARNING) {
                if (throwable == null) this.logger.warn(message);
                else                   this.logger.warn(message, throwable);
            } else if (level >= CONFIG) {
                if (throwable == null) this.logger.info(message);
                else                   this.logger.info(message, throwable);
            } else if (level >= FINE) {
                if (throwable == null) this.logger.debug(message);
                else                   this.logger.debug(message, throwable);
            } else if (level >= FINEST) {
                if (throwable == null) this.logger.trace(message);
                else                   this.logger.trace(message, throwable);
            }

            logger = logger.getParent();
        }
    }

    /* ====================================================================== */

    @Override
    public void setLevel(Level newLevel) {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    @Override
    public void addHandler(Handler handler) {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    @Override
    public void removeHandler(Handler handler) {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

}
