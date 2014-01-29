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
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JavaLoggingBridge extends LogManager {

    public JavaLoggingBridge() {
        super();
    }

    public JavaLoggingAdapter getLogger(String name) {
        return new JavaLoggingAdapter(name);
    }

    /* ====================================================================== */
    /* Utterly useless methods                                                */
    /* ====================================================================== */

    public boolean addLogger(Logger logger) {
        return false;
    }

    public Enumeration<String> getLoggerNames() {
        return new Vector<String>().elements();
    }

    public String getProperty(String name) {
        return null;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        return;
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        return;
    }

    public void reset() {
        return;
    }

    /* ====================================================================== */
    /* Even more utterly useless methods                                      */
    /* ====================================================================== */

    public void checkAccess() {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    public void readConfiguration() {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

    public void readConfiguration(InputStream inputStream) {
        throw new UnsupportedOperationException("Sorry, Dave, I can't let you do that");
    }

}
