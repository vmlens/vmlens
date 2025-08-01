// SPDX-License-Identifier: Apache-2.0
package com.vmlens.inttest.projects.hiero;



/**
 */
@FunctionalInterface
public interface Locked extends AutoCloseable {
    /**
     * Unlocks the previously acquired lock
     */
    @Override
    void close();
}
