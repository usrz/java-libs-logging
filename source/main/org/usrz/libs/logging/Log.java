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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Our own wrapper around all various logging packages (this decouples our
 * code from whatever "next" better API will come up in the next 5 minutes).
 *
 * <p>As with all other packages, our log levels are the standard ones:</p>
 *
 * <ul>
 *   <li><b>ERROR</b>: huh? Houston, we seem to have a problem!</li>
 *   <li><b>WARN</b>: really, this should be looked at... Did you feed the cats?</li>
 *   <li><b>INFO</b>: bah, boring reporting of whatever happened here and there.</li>
 *   <li><b>DEBUG</b>: this might help, somehow, somewhere, when code looks good, but bugs are found.</li>
 *   <li><b>TRACE</b>: you're an annoying, little, curious child, aren't you?</li>
 * </ul>
 * <p><b><i>NOTE:</i> This is NOT a logging API, you are NOT allowed to use it
 * in your code, and anyone who relies on this (besides me) should be beaten
 * with a friggin' clue-stick. Go write your own wrapper if you don't like the
 * style of API that SLF4J provides, but DO NOT use this (yes, you are allowed
 * to copy and paste it in your own package).</b></p>
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public class Log {

    static { Logging.init(); }

    public static final Log ROOT_LOG = new Log(Logger.ROOT_LOGGER_NAME);

    private final Logger logger;

    public Log() {
        logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());
    }

    public Log(Class<?> clazz) {
        new Throwable().printStackTrace();
        logger = LoggerFactory.getLogger(clazz != null ? clazz.getName() :
                                         new Throwable().getStackTrace()[1].getClassName());
    }

    public Log(String name) {
        logger = LoggerFactory.getLogger(name != null ? name :
                                         new Throwable().getStackTrace()[1].getClassName());
    }

    /* ====================================================================== */

    /**
     * Return the name of this {@link Log}.
     */
    public String getName() {
        return logger.getName();
    }

    /**
     * Usual {@link String} representation FTW.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[" + getName() + "]@" + hashCode();
    }

    /*========================================================================*
     *  Here follows a list of the most boring, repetitive code I ever wrote  *
     *========================================================================*/

    /**
     * Check if the <b>TRACE</b> level is enabled.
     * <p>
     * Don't use this in your logging code, we'll check first thing in here.
     *
     * @deprecated Because you shouldn't use it!
     */
    @Deprecated
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>TRACE</b> level.
     */
    public Log trace(String message) {
        if (logger.isTraceEnabled()) {
            logger.trace(message);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(String format, Object parameter1) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(String format, Object parameter1, Object parameter2) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>TRACE</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(String format, Object... parameters) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameters));
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>TRACE</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #trace(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log trace(String message, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>TRACE</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #trace(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log trace(Throwable throwable, String message) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(Throwable throwable, String format, Object parameter1) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1),
                        throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>TRACE</b> level.
     * <p>
     * This is exactly the same as {@link #trace(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>TRACE</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log trace(Throwable throwable, String format, Object... parameters) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format(format == null ? "Null format" : format,
                                       parameters),
                         throwable);
        }
        return this;
    }

    /* ====================================================================== */

    /**
     * Check if the <b>DEBUG</b> level is enabled.
     * <p>
     * Don't use this in your logging code, we'll check first thing in here.
     *
     * @deprecated Because you shouldn't use it!
     */
    @Deprecated
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>DEBUG</b> level.
     */
    public Log debug(String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(String format, Object parameter1) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(String format, Object parameter1, Object parameter2) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>DEBUG</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(String format, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameters));
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>DEBUG</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #debug(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log debug(String message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>DEBUG</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #debug(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log debug(Throwable throwable, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(Throwable throwable, String format, Object parameter1) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1),
                        throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>DEBUG</b> level.
     * <p>
     * This is exactly the same as {@link #debug(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>DEBUG</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log debug(Throwable throwable, String format, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(format == null ? "Null format" : format,
                                       parameters),
                         throwable);
        }
        return this;
    }

    /* ====================================================================== */

    /**
     * Check if the <b>INFO</b> level is enabled.
     * <p>
     * Don't use this in your logging code, we'll check first thing in here.
     *
     * @deprecated Because you shouldn't use it!
     */
    @Deprecated
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>INFO</b> level.
     */
    public Log info(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(String format, Object parameter1) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(String format, Object parameter1, Object parameter2) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>INFO</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log info(String format, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameters));
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>INFO</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #info(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log info(String message, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            logger.info(message, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>INFO</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #info(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log info(Throwable throwable, String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(Throwable throwable, String format, Object parameter1) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1),
                        throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>INFO</b> level.
     * <p>
     * This is exactly the same as {@link #info(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log info(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>INFO</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log info(Throwable throwable, String format, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(format == null ? "Null format" : format,
                                       parameters),
                         throwable);
        }
        return this;
    }

    /* ====================================================================== */

    /**
     * Check if the <b>WARN</b> level is enabled.
     * <p>
     * Don't use this in your logging code, we'll check first thing in here.
     *
     * @deprecated Because you shouldn't use it!
     */
    @Deprecated
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>WARN</b> level.
     */
    public Log warn(String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(String format, Object parameter1) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(String format, Object parameter1, Object parameter2) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>WARN</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(String format, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameters));
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>WARN</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #warn(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log warn(String message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            logger.warn(message, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>WARN</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #warn(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log warn(Throwable throwable, String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(Throwable throwable, String format, Object parameter1) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1),
                        throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>WARN</b> level.
     * <p>
     * This is exactly the same as {@link #warn(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>WARN</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log warn(Throwable throwable, String format, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(format == null ? "Null format" : format,
                                       parameters),
                         throwable);
        }
        return this;
    }

    /* ====================================================================== */

    /**
     * Check if the <b>ERROR</b> level is enabled.
     * <p>
     * Don't use this in your logging code, we'll check first thing in here.
     *
     * @deprecated Because you shouldn't use it!
     */
    @Deprecated
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>ERROR</b> level.
     */
    public Log error(String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(String format, Object parameter1) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(String format, Object parameter1, Object parameter2) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3));
        }
        return this;
    }

    /**
     * Log a formatted message at <b>ERROR</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log error(String format, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameters));
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>ERROR</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #error(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log error(String message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(message, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>ERROR</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #error(Throwable, String)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public Log error(Throwable throwable, String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(Throwable throwable, String format, Object parameter1) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1),
                        throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>ERROR</b> level.
     * <p>
     * This is exactly the same as {@link #error(Throwable, String, Object...)}
     * but does not incur in the (minimal) time needed to create an array.
     *
     * @see String#format(String, Object...)
     */
    public Log error(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameter1, parameter2, parameter3),
                         throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>ERROR</b> level.
     *
     * @see String#format(String, Object...)
     */
    public Log error(Throwable throwable, String format, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(String.format(format == null ? "Null format" : format,
                                       parameters),
                         throwable);
        }
        return this;
    }
}
