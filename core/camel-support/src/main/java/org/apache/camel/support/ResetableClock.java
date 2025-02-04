/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.support;

import org.apache.camel.Clock;

public final class ResetableClock implements Clock {
    private long created;

    ResetableClock(Clock clock) {
        this.created = clock.getCreated();
    }

    ResetableClock() {
        this.created = System.currentTimeMillis();
    }

    @Override
    public long elapsed() {
        return System.currentTimeMillis() - created;
    }

    @Override
    public long getCreated() {
        return created;
    }

    /**
     * Reset the clock to the current point in time
     */
    public void reset() {
        this.created = System.currentTimeMillis();
    }

    /**
     * Unset the clock (set to zero). This is part of the pooling exchange support, so that the exchange can be marked
     * as done and reused
     */
    void unset() {
        this.created = 0;
    }
}
