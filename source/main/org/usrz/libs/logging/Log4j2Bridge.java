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

/**
 * A logging bridge (or in other words a <i>logger factory<i> implementation) for
 * <a href="http://logging.apache.org/log4j/2.x/">Apache Log4j 2</a>.
 *
 * @author <a href="mailto:pier@usrz.com">Pier Fumagalli</a>
 */
public final class Log4j2Bridge
implements LoggerContextFactory, LoggerContext {

    static Log4j2Adapter LAST_ENTRY;

    public Log4j2Bridge() {
        /* Do nothing */
    }

    /* ====================================================================== */

    public static Log4j2Adapter getLastEntry() {
        return LAST_ENTRY;
    }

    /* ====================================================================== */

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, boolean currentContext) {
        return this;
    }

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, boolean currentContext, URI configLocation) {
        return this;
    }

    /* ====================================================================== */

    @Override
    public void removeContext(LoggerContext context) {
        /* Nothing to do */
    }

    @Override
    public Object getExternalContext() {
        return null;
    }

    @Override
    public Logger getLogger(String name) {
        return new Log4j2Adapter(name);
    }

    @Override
    public Logger getLogger(String name, MessageFactory factory) {
        return new Log4j2Adapter(name, factory);
    }

    @Override
    public boolean hasLogger(String name) {
        return true;
    }

    /* ====================================================================== */
}
