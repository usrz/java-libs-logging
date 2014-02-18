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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * Our own wrapper around all various logging packages (this decouples our
 * code from whatever "next" better API will come up in the next 5 minutes).
 *
 * <p>To use simply slap <code>private static final Log log = new Log()</code>
 * at the top of your class file and stop worring about it.</p>
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
 *
 * <p>If I may complain a bit, <i>SLF4J</i> does not provide the <b>FATAL</b>
 * level which I find pretty darn useful, but fuck-it, for now this'll do.</p>
 *
 * <p>Oh, and in case you wondered why I wrote this, rather than using SLF4J's
 * own {@link Logger} interface, here are few reasons I just came up with:</p>
 *
 * <ul>
 *   <li>You can't format a message <i>and</i> specify a {@link Throwable}...
 *       Dunno, maybe it checks if the last parameter is one, and the format
 *       has only N-1 formats... TL;DR, I want shit to be explicit!</li>
 *   <li>What's the deal with <code>{}</code>??? I mean, are we all retards or
 *       can't we just use that nice syntax we use since the late 1970s and
 *       wondefully explained in Brian Kernighan's and Dennis Ritchie's
 *       <a href="http://en.wikipedia.org/wiki/The_C_Programming_Language">The
 *       C Programming Language</a>? {@link String#format(String, Object...)}
 *       for the win.</li>
 *   <li>We are in 201X (with X >= 4)... Still factory methods to create
 *       instances? The <code>new</code> keyword rules, and if the underlying
 *       stuff is heavy to construct, just wrap it, it's cheap.</li>
 *   <li>I <code>WANT</code> a default <em>instantiator</em> somehow... It's
 *       great that I can construct a {@link Logger} with either a
 *       {@link String} name or a {@link Class} instance, and if those are
 *       <b>null</b> somehow you're going to do magic. <b>NO MAGIC</b>: tell
 *       me what is going to happen straight away, under my fingertips in my
 *       favorite IDE.</li>
 * </ul>
 *
 * <p>Logging sucks: a trivial problem bloated by years of stupid flamewars.</p>
 *
 * <p><b><i>NOTE:</i> This is NOT a logging API, you are NOT allowed to use it
 * in your code, and anyone who relies on this (besides me) should be beaten
 * with a friggin' clue-stick. Go write your own wrapper if you don't like the
 * style of API that SLF4J provides, but DO NOT use this (yes, you are allowed
 * to copy and paste it in your own package).</b></p>
 *
 * <p><i>PS:</i> This class will statically call {@link Logging#init()}
 * whether you want it or not!</p>
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final  class Log {

    static { Logging.init(); }

    /**
     * The <i>ROOT</i> logger (or the logger with no name, the "unnamed").
     *
     * @see <a href="http://bit.ly/1e7NM1t">The Unnamed</a>
     */
    public final static Log ROOT_LOG = new Log(Logger.ROOT_LOGGER_NAME);

    /* The fully qualified class name of this class */
    private static final String FQCN = Log.class.getName();
    /* Our "location aware" logger */
    private final LocationAwareLogger logger;

    /**
     * Create a new {@link Log} using the caller class name as the name.
     */
    public Log() {
        this(LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName()));
    }

    /**
     * Create a new {@link Log}; if the specified {@link Class} is
     * <b>null</b> the caller class name will be used as a name.</p>
     */
    public Log(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz != null ? clazz.getName() :
                                     new Throwable().getStackTrace()[1].getClassName()));
    }

    /**
     * Create a new {@link Log}: if the specified {@link String} is
     * <b>null</b> the caller class name will be used as a name.</p>
     */
    public Log(String name) {
        this(LoggerFactory.getLogger(name != null ? name :
                                     new Throwable().getStackTrace()[1].getClassName()));
    }

    /* ====================================================================== */

    private Log(Logger logger) {
        this.logger = SLF4JFactory.asLocationAwareLogger(logger);
    }

    /* ====================================================================== */

    /**
     * Return the name of this {@link Log}.
     */
    public final String getName() {
        return logger.getName();
    }

    /**
     * Usual {@link String} representation FTW.
     */
    @Override
    public final String toString() {
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
    public final boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>TRACE</b> level.
     */
    public final Log trace(String message) {
        if (logger.isTraceEnabled()) {
            logger.log(null, FQCN, TRACE_INT, message, null, null);
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
    public final Log trace(String format, Object parameter1) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, TRACE_INT, message, null, null);
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
    public final Log trace(String format, Object parameter1, Object parameter2) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, TRACE_INT, message, null, null);
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
    public final Log trace(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, TRACE_INT, message, null, null);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>TRACE</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log trace(String format, Object... parameters) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, TRACE_INT, message, null, null);
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
    public final Log trace(String message, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>TRACE</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #trace(String, Throwable)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public final Log trace(Throwable throwable, String message) {
        if (logger.isTraceEnabled()) {
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
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
    public final Log trace(Throwable throwable, String format, Object parameter1) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
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
    public final Log trace(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
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
    public final Log trace(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>TRACE</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log trace(Throwable throwable, String format, Object... parameters) {
        if (logger.isTraceEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, TRACE_INT, message, null, throwable);
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
    public final boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>DEBUG</b> level.
     */
    public final Log debug(String message) {
        if (logger.isDebugEnabled()) {
            logger.log(null, FQCN, DEBUG_INT, message, null, null);
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
    public final Log debug(String format, Object parameter1) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, DEBUG_INT, message, null, null);
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
    public final Log debug(String format, Object parameter1, Object parameter2) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, DEBUG_INT, message, null, null);
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
    public final Log debug(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, DEBUG_INT, message, null, null);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>DEBUG</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log debug(String format, Object... parameters) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, DEBUG_INT, message, null, null);
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
    public final Log debug(String message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>DEBUG</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #debug(String, Throwable)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public final Log debug(Throwable throwable, String message) {
        if (logger.isDebugEnabled()) {
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
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
    public final Log debug(Throwable throwable, String format, Object parameter1) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
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
    public final Log debug(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
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
    public final Log debug(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>DEBUG</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log debug(Throwable throwable, String format, Object... parameters) {
        if (logger.isDebugEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, DEBUG_INT, message, null, throwable);
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
    public final boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>INFO</b> level.
     */
    public final Log info(String message) {
        if (logger.isInfoEnabled()) {
            logger.log(null, FQCN, INFO_INT, message, null, null);
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
    public final Log info(String format, Object parameter1) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, INFO_INT, message, null, null);
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
    public final Log info(String format, Object parameter1, Object parameter2) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, INFO_INT, message, null, null);
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
    public final Log info(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, INFO_INT, message, null, null);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>INFO</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log info(String format, Object... parameters) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, INFO_INT, message, null, null);
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
    public final Log info(String message, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>INFO</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #info(String, Throwable)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public final Log info(Throwable throwable, String message) {
        if (logger.isInfoEnabled()) {
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
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
    public final Log info(Throwable throwable, String format, Object parameter1) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
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
    public final Log info(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
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
    public final Log info(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>INFO</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log info(Throwable throwable, String format, Object... parameters) {
        if (logger.isInfoEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, INFO_INT, message, null, throwable);
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
    public final boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>WARN</b> level.
     */
    public final Log warn(String message) {
        if (logger.isWarnEnabled()) {
            logger.log(null, FQCN, WARN_INT, message, null, null);
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
    public final Log warn(String format, Object parameter1) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, WARN_INT, message, null, null);
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
    public final Log warn(String format, Object parameter1, Object parameter2) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, WARN_INT, message, null, null);
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
    public final Log warn(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, WARN_INT, message, null, null);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>WARN</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log warn(String format, Object... parameters) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, WARN_INT, message, null, null);
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
    public final Log warn(String message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>WARN</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #warn(String, Throwable)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public final Log warn(Throwable throwable, String message) {
        if (logger.isWarnEnabled()) {
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
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
    public final Log warn(Throwable throwable, String format, Object parameter1) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
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
    public final Log warn(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
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
    public final Log warn(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>WARN</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log warn(Throwable throwable, String format, Object... parameters) {
        if (logger.isWarnEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, WARN_INT, message, null, throwable);
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
    public final boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message at <b>ERROR</b> level.
     */
    public final Log error(String message) {
        if (logger.isErrorEnabled()) {
            logger.log(null, FQCN, ERROR_INT, message, null, null);
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
    public final Log error(String format, Object parameter1) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, ERROR_INT, message, null, null);
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
    public final Log error(String format, Object parameter1, Object parameter2) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, ERROR_INT, message, null, null);
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
    public final Log error(String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, ERROR_INT, message, null, null);
        }
        return this;
    }

    /**
     * Log a formatted message at <b>ERROR</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log error(String format, Object... parameters) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, ERROR_INT, message, null, null);
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
    public final Log error(String message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
        }
        return this;
    }

    /* -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - */

    /**
     * Log a simple message and a {@link Throwable} at <b>ERROR</b> level.
     *
     * <p><b>NOTE:</b> This is equivalent to {@link #error(String, Throwable)}
     * but is included with the parameters reversed because of "habit".</p>
     */
    public final Log error(Throwable throwable, String message) {
        if (logger.isErrorEnabled()) {
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
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
    public final Log error(Throwable throwable, String format, Object parameter1) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1);
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
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
    public final Log error(Throwable throwable, String format, Object parameter1, Object parameter2) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2);
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
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
    public final Log error(Throwable throwable, String format, Object parameter1, Object parameter2, Object parameter3) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameter1, parameter2, parameter3);
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
        }
        return this;
    }

    /**
     * Log a formatted message and an {@link Throwable} at <b>ERROR</b> level.
     *
     * @see String#format(String, Object...)
     */
    public final Log error(Throwable throwable, String format, Object... parameters) {
        if (logger.isErrorEnabled()) {
            final String message = String.format(format == null ? "Null format" : format, parameters);
            logger.log(null, FQCN, ERROR_INT, message, null, throwable);
        }
        return this;
    }
}
