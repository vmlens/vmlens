// SPDX-License-Identifier: Apache-2.0
package com.vmlens.inttest.projects.hiero;

import edu.umd.cs.findbugs.annotations.NonNull;


import java.util.concurrent.locks.ReentrantLock;

/**
 * Factory for all custom Locks. Should be used as a facade for the API.
 */
public interface Locks {

    /**
     * Create a new lock for index values.
     *
     * @param parallelism
     * 		the number of unique locks. Higher parallelism reduces chances of collision for non-identical
     * 		* 		indexes at the cost of additional memory overhead.
     * @return a new lock for index values.
     */
    @NonNull
    static IndexLock createIndexLock(final int parallelism) {
        return new DefaultIndexLock(parallelism);
    }


}
