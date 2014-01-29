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

import org.testng.annotations.Test;

public class LogNamingTest {

    static { Logging.init(); }

    private static final Log log = new Log();

    @Test
    public void testNamedLog() {
        assertEquals(new Log("fooBar!").getName(), "fooBar!");
    }

    @Test
    public void testClassNameLog() {
        assertEquals(new Log(String.class).getName(), "java.lang.String");
    }

    @Test
    public void testClassLog() {
        assertEquals(log.getName(), this.getClass().getName());
    }

    @Test
    public void testMethodLog() {
        assertEquals(new Log().getName(), this.getClass().getName());
    }

    @Test
    public void testAnonymousInnerClassLog() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                assertEquals(new Log().getName(), this.getClass().getName());
            }
        };
        runnable.run();
    }

    @Test
    public void testStaticInnerClassLog() {
        new StaticRunnable().run();
    }

    @Test
    public void testInstanceInnerClassLog() {
        new InstanceRunnable().run();
    }

    /* ====================================================================== */

    private static class StaticRunnable implements Runnable {

        private static final Log classLog = new Log();

        @Override
        public void run() {
            assertEquals(classLog.getName(), this.getClass().getName(), "Class log name mismatch");
            assertEquals(new Log().getName(), this.getClass().getName(), "Method log name mismatch");
        }

    }

    private class InstanceRunnable implements Runnable {

        private final Log classLog = new Log();

        @Override
        public void run() {
            assertEquals(classLog.getName(), this.getClass().getName(), "Class log name mismatch");
            assertEquals(new Log().getName(), this.getClass().getName(), "Method log name mismatch");
        }

    }

}
