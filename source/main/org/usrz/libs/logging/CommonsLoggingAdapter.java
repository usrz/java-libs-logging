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

import org.apache.commons.logging.Log;
import org.slf4j.Logger;

/**
 * A logging adapter (or in other words a <i>logger<i> implementation) for
 * <a href="http://commons.apache.org/proper/commons-logging/">Apache Commons Logging</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class CommonsLoggingAdapter implements Log {

    private final Logger logger;

    protected CommonsLoggingAdapter(String className) {
        logger = org.slf4j.LoggerFactory.getLogger(className);
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
            if (message == null) logger.trace("Null message");
            else try {
                logger.trace((String) message);
            } catch (ClassCastException exception) {
                logger.trace("{}", message);
            }
        }
    }

    @Override
    public void trace(Object message, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            if (message == null) logger.trace("Null message", throwable);
            else try {
                logger.trace((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.trace("{}", message, throwable);
            }
        }
    }

    @Override
    public void debug(Object message) {
        if (logger.isDebugEnabled()) {
            if (message == null) logger.debug("Null message");
            else try {
                logger.debug((String) message);
            } catch (ClassCastException exception) {
                logger.debug("{}", message);
            }
        }
    }

    @Override
    public void debug(Object message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            if (message == null) logger.debug("Null message", throwable);
            else try {
                logger.debug((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.debug("{}", message, throwable);
            }
        }
    }

    @Override
    public void info(Object message) {
        if (logger.isInfoEnabled()) {
            if (message == null) logger.info("Null message");
            else try {
                logger.info((String) message);
            } catch (ClassCastException exception) {
                logger.info("{}", message);
            }
        }
    }

    @Override
    public void info(Object message, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            if (message == null) logger.info("Null message", throwable);
            else try {
                logger.info((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.info("{}", message, throwable);
            }
        }
    }

    @Override
    public void warn(Object message) {
        if (logger.isWarnEnabled()) {
            if (message == null) logger.warn("Null message");
            else try {
                logger.warn((String) message);
            } catch (ClassCastException exception) {
                logger.warn("{}", message);
            }
        }
    }

    @Override
    public void warn(Object message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            if (message == null) logger.warn("Null message", throwable);
            else try {
                logger.warn((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.warn("{}", message, throwable);
            }
        }
    }

    @Override
    public void error(Object message) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.error("Null message");
            else try {
                logger.error((String) message);
            } catch (ClassCastException exception) {
                logger.error("{}", message);
            }
        }
    }

    @Override
    public void error(Object message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.error("Null message", throwable);
            else try {
                logger.error((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.error("{}", message, throwable);
            }
        }
    }

    @Override
    public void fatal(Object message) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.error("Null message");
            else try {
                logger.error((String) message);
            } catch (ClassCastException exception) {
                logger.error("{}", message);
            }
        }
    }

    @Override
    public void fatal(Object message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            if (message == null) logger.error("Null message", throwable);
            else try {
                logger.error((String) message, throwable);
            } catch (ClassCastException exception) {
                logger.error("{}", message, throwable);
            }
        }
    }

}
