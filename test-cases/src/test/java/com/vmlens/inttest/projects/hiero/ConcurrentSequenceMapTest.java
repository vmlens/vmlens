// SPDX-License-Identifier: Apache-2.0
package com.vmlens.inttest.projects.hiero;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class ConcurrentSequenceMapTest {

    private class SequenceMapKey {

        private final int key;
        private final long sequence;

        public SequenceMapKey(int key, long sequence) {
            this.key = key;
            this.sequence = sequence;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof  SequenceMapKey ) {
                return this.key == ((SequenceMapKey)obj).key;
            }
            return false;
        }

        @Override
        public String toString() {
            return key + "[" + sequence + "]";
        }

        public long getSequence() {
            return sequence;
        }
    }

    @Test
    public void putIfAbsentAndGetKeysWithSequenceNumber() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("putIfAbsentAndGetKeysWithSequenceNumber")) {
            while (allInterleavings.hasNext()) {
                SequenceMap<SequenceMapKey, Integer> map = new  ConcurrentSequenceMap<>(0, 1, true, SequenceMapKey::getSequence);
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        map.remove(new SequenceMapKey(1, 5L));
                    }
                };
                first.start();
                map.putIfAbsent(new SequenceMapKey(2, 780L), 7);
                first.join();
            }
        }
    }
}
