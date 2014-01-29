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

import org.apache.commons.logging.LogFactory;

public class CommonsLoggingBridge extends LogFactory {

    @Override @SuppressWarnings("rawtypes")
    public CommonsLoggingAdapter getInstance(Class clazz) {
        return this.getInstance(clazz == null ? null : clazz.getName());
    }

    @Override
    public CommonsLoggingAdapter getInstance(String name) {
        return new CommonsLoggingAdapter(name);
    }

    /* ====================================================================== */
    /* Utterly useless methods                                                */
    /* ====================================================================== */

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public String[] getAttributeNames() {
        return new String[0];
    }

    @Override
    public void release() {
        /* Nothing to do */
    }

    @Override
    public void removeAttribute(String name) {
        /* Nothing to do */
    }

    @Override
    public void setAttribute(String name, Object value) {
        throw new UnsupportedOperationException("You don't need this");
    }

}
