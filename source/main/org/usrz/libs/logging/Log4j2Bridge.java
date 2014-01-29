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

import java.net.URI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

public class Log4j2Bridge
implements LoggerContextFactory, LoggerContext {

    static Log4j2Adapter LAST_ENTRY;

    /* ====================================================================== */

    public static Log4j2Adapter getLastEntry() {
        return LAST_ENTRY;
    }

    /* ====================================================================== */

    public LoggerContext getContext(String fqcn, ClassLoader loader, boolean currentContext) {
        return this;
    }

    public LoggerContext getContext(String fqcn, ClassLoader loader, boolean currentContext, URI configLocation) {
        return this;
    }

    /* ====================================================================== */

    public void removeContext(LoggerContext context) {
        /* Nothing to do */
    }

    public Object getExternalContext() {
        return null;
    }

    public Logger getLogger(String name) {
        return new Log4j2Adapter(name);
    }

    public Logger getLogger(String name, MessageFactory factory) {
        return new Log4j2Adapter(name, factory);
    }

    public boolean hasLogger(String name) {
        return true;
    }

    /* ====================================================================== */
}
