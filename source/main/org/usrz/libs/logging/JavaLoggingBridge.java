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

import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * A logging bridge (or in other words a <i>logger factory<i> implementation) for
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html">Java's Logging API (<i>java.util.logging</i>)</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class JavaLoggingBridge extends LogManager {

    private final Logger disabled;

    public JavaLoggingBridge() {
        /* This fixes some issues in JDK8's Nashorn */
        disabled = new Logger("disabled", null) {
            @Override public void log(LogRecord record) {}
            @Override public Level getLevel() { return Level.OFF; }
        };
    }

    @Override
    public Logger getLogger(String name) {
        if (name.equals("disabled")) return disabled;
        return new JavaLoggingAdapter(name);
    }

    /* ====================================================================== */
    /* Utterly useless methods                                                */
    /* ====================================================================== */

    @Override
    public boolean addLogger(Logger logger) {
        return false;
    }

    @Override
    public Enumeration<String> getLoggerNames() {
        return new Vector<String>().elements();
    }

    @Override
    public String getProperty(String name) {
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        return;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        return;
    }

    @Override
    public void reset() {
        return;
    }

    /* ====================================================================== */
    /* Even more utterly useless methods                                      */
    /* ====================================================================== */

    @Override
    public void checkAccess() {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    @Override
    public void readConfiguration() {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    @Override
    public void readConfiguration(InputStream inputStream) {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

}
