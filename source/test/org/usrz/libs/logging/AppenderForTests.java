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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;

public class AppenderForTests<E extends ILoggingEvent>
extends AppenderBase<E> implements Appender<E> {

    private static final AtomicReference<LogEvent> LAST_EVENT = new AtomicReference<>();

    @Override
    protected void append(E eventObject) {
        assertTrue(LAST_EVENT.compareAndSet(null, new LogEvent(eventObject)), "Last event [" + LAST_EVENT.get().message + "] not cleared");
    }

    public static void hasNoLastEvent(String message) {
        final LogEvent event = LAST_EVENT.get();
        assertNull(event, message == null ? "Last event is NOT null" : message);
    }

    public static LogEvent hasLastEvent() {
        return hasLastEvent(null);
    }

    public static LogEvent hasLastEvent(String message) {
        final LogEvent event = LAST_EVENT.get();
        assertNotNull(event, message == null ? "Last event is null" : message);
        assertTrue(LAST_EVENT.compareAndSet(event, null), "Can't clear last event");
        return event;
    }

    public static class LogEvent {

        private final String className;
        private final Level level;
        private final String message;
        private final IThrowableProxy throwable;
        private final String caller;

        private LogEvent(ILoggingEvent event) {
            this.className = event.getLoggerName();
            this.level = event.getLevel();
            this.message = event.getFormattedMessage();
            this.throwable = event.getThrowableProxy();
            this.caller = event.getCallerData()[0].getClassName();
        }

        public LogEvent assertCaller(Object caller) {
            assertEquals(this.caller, caller.getClass().getName());
            return this;
        }

        public LogEvent assertClass(Class<?> clazz) {
            return assertClassName(clazz.getName());
        }

        public LogEvent assertClassName(String className) {
            assertEquals(this.className, className, "Wrong class name");
            return this;
        }

        public LogEvent assertLevel(Level level) {
            assertEquals(this.level, level, "Wrong logging level");
            return this;
        }

        public LogEvent assertMessage(String message) {
            assertEquals(this.message, message, "Wrong message");
            return this;
        }

        public LogEvent assertThrowable(Throwable throwable) {
            if (throwable == null) {
                assertNull(this.throwable, "Throwable is not null");
                return this;
            }

            assertNotNull(this.throwable, "Throwable is null");
            assertEquals(this.throwable.getMessage(), throwable.getMessage(), "Wrong throwable message");
            assertEquals(this.throwable.getClassName(), throwable.getClass().getName(), "Wrong throwable class name");
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() +
                    "[class=" + this.className +
                    ",level=" + this.level +
                    ",message=" + this.message +
                    (this.throwable == null ? "" : throwable.getClass()) +
                    "]@" + hashCode();
        }
    }

}
