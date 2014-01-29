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

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;

public class Log4j1Bridge implements RepositorySelector, LoggerRepository {

    public Log4j1Bridge() {
        /* Do nothing */
    }

    public LoggerRepository getLoggerRepository() {
        return this;
    }

    public Logger getLogger(final String name) {
        return new Log4j1Adapter(this, name);
    }

    public Logger getLogger(final String name, final LoggerFactory factory) {
        return new Log4j1Adapter(this, name);
    }

    public Logger getRootLogger() {
        return new Log4j1Adapter(this, ROOT_LOGGER_NAME);
    }

    /* ====================================================================== */
    /* Utterly useless methods                                                */
    /* ====================================================================== */

    public void addHierarchyEventListener(final HierarchyEventListener listener) {
        /* Do nothing */
    }

    public boolean isDisabled(final int level) {
        return false;
    }

    public void setThreshold(final Level level) {
        /* Do nothing */
    }

    public void setThreshold(final String val) {
        /* Do nothing */
    }

    public void emitNoAppenderWarning(final Category cat) {
        /* Do nothing */
    }

    public Level getThreshold() {
        return Level.ALL;
    }

    public Logger exists(final String name) {
        return null;
    }

    public void shutdown() {
        /* Do nothing */
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getCurrentLoggers() {
        return new Vector().elements();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getCurrentCategories() {
        return getCurrentLoggers();
    }

    public  void fireAddAppenderEvent(Category logger, Appender appender) {
        /* Do nothing */
    }

    public void resetConfiguration() {
        /* Do nothing */
    }
}
