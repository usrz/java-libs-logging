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

import static org.slf4j.spi.LocationAwareLogger.DEBUG_INT;
import static org.slf4j.spi.LocationAwareLogger.ERROR_INT;
import static org.slf4j.spi.LocationAwareLogger.INFO_INT;
import static org.slf4j.spi.LocationAwareLogger.TRACE_INT;
import static org.slf4j.spi.LocationAwareLogger.WARN_INT;

import org.apache.commons.logging.Log;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://commons.apache.org/proper/commons-logging/">Apache Commons Logging</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class CommonsLoggingAdapter implements Log {

    private static final String FQCN = CommonsLoggingAdapter.class.getName();

    private final LocationAwareLogger logger;

    protected CommonsLoggingAdapter(String className) {
        logger = (LocationAwareLogger) org.slf4j.LoggerFactory.getLogger(className);
    }

    /* ====================================================================== */

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return logger.isErrorEnabled();
    }

    /* ====================================================================== */

    @Override
    public void trace(Object message) {
        if (logger.isTraceEnabled()) {
            if (message == null) logger.log(null, FQCN, TRACE_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, TRACE_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, TRACE_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void trace(Object message, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            if (message == null) logger.log(null, FQCN, TRACE_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, TRACE_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, TRACE_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

    @Override
    public void debug(Object message) {
        if (logger.isDebugEnabled()) {
            if (message == null) logger.log(null, FQCN, DEBUG_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, DEBUG_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, DEBUG_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void debug(Object message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            if (message == null) logger.log(null, FQCN, DEBUG_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, DEBUG_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, DEBUG_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

    @Override
    public void info(Object message) {
        if (logger.isInfoEnabled()) {
            if (message == null) logger.log(null, FQCN, INFO_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, INFO_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, INFO_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void info(Object message, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            if (message == null) logger.log(null, FQCN, INFO_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, INFO_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, INFO_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

    @Override
    public void warn(Object message) {
        if (logger.isWarnEnabled()) {
            if (message == null) logger.log(null, FQCN, WARN_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, WARN_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, WARN_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void warn(Object message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            if (message == null) logger.log(null, FQCN, WARN_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, WARN_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, WARN_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

    @Override
    public void error(Object message) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.log(null, FQCN, ERROR_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, ERROR_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, ERROR_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void error(Object message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.log(null, FQCN, ERROR_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, ERROR_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, ERROR_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

    @Override
    public void fatal(Object message) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.log(null, FQCN, ERROR_INT, "Null message", null, null);
            else try {
                logger.log(null, FQCN, ERROR_INT, (String) message, null, null);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, ERROR_INT, "{}", new Object[] { message }, null);
            }
        }
    }

    @Override
    public void fatal(Object message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.log(null, FQCN, ERROR_INT, "Null message", null, throwable);
            else try {
                logger.log(null, FQCN, ERROR_INT, (String) message, null, throwable);
            } catch (ClassCastException exception) {
                logger.log(null, FQCN, ERROR_INT, "{}", new Object[] { message }, throwable);
            }
        }
    }

}
