package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.KeyToThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Monitor;
import gnu.trove.map.hash.THashMap;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BlockingLockRelationBuilderTest {

    private final Monitor first = new Monitor(16L);
    private final Monitor second = new Monitor(36L);
    private final Monitor third = new Monitor(120L);

    @Test
    public void threeMonitorsSingleThreaded() {
        KeyToThreadIdToElementList<Object, DependentBlock> map = threeMonitors(6, 6);
        assertThat(map.map().size(),is(0));
    }

    @Test
    public void threeMonitorsTwoThreads() {
        KeyToThreadIdToElementList<Object, DependentBlock> map = threeMonitors(6, 9);
        THashMap<Object, ThreadIndexToElementList<DependentBlock>> result = map.map();
        assertThat(result.size(),is(3));
        assertThat(result.containsKey(new LockPair(first.key(),second.key())),is(true));
        assertThat(result.containsKey(new LockPair(first.key(),third.key())),is(true));
        assertThat(result.containsKey(new LockPair(second.key(),third.key())),is(true));
    }

    private KeyToThreadIdToElementList<Object, DependentBlock> threeMonitors(int firstThreadIndex, int secondThreadIndex) {
        // Given
        BlockingLockRelationBuilder builder = new BlockingLockRelationBuilder();
        LockFactory firstThread = new LockFactory(firstThreadIndex);
        LockFactory secondThread = new LockFactory(secondThreadIndex);

        // When
        builder.onLockEnter(firstThread.enter(first));
        builder.onLockEnter(firstThread.enter(second));
        builder.onLockEnter(firstThread.enter(third));

        builder.onLockEnter(secondThread.enter(third));
        builder.onLockEnter(secondThread.enter(second));
        builder.onLockEnter(secondThread.enter(first));

        return builder.build().build();
    }
}
