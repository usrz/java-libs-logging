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

public final class CommonsLoggingAdapter implements Log {

    private final Logger logger;

    protected CommonsLoggingAdapter(String className) {
        logger = org.slf4j.LoggerFactory.getLogger(className);
    }

    /* ====================================================================== */

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public boolean isFatalEnabled() {
        return logger.isErrorEnabled();
    }

    /* ====================================================================== */

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
