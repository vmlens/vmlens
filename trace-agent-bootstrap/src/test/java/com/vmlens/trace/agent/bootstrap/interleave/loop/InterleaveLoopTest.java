package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.loop.volatilereadwrite.*;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop.create;
import static com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop.isSame;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveLoopTest {

    @Test
    public void isSameForDifferentHashCodeAndDifferentOrder() {
        ThreadIndexToElementList<InterleaveAction> a = create(new DifferentHashCodeA().build());
        ThreadIndexToElementList<InterleaveAction> b = create(new DifferentHashCodeB().build());
        ThreadIndexToElementList<InterleaveAction> c = create(new DifferentHashCodeC().build());

        assertThat(isSame(a,b),is(true));
        assertThat(isSame(b,a),is(true));
        assertThat(isSame(b,c),is(true));
        assertThat(isSame(c,b),is(true));
        assertThat(isSame(a,c),is(true));
        assertThat(isSame(c,a),is(true));
    }

    @Test
    public void isDifferentForDifferentFieldId() {
        ThreadIndexToElementList<InterleaveAction> a = create(new DifferentFieldIdA().build());
        ThreadIndexToElementList<InterleaveAction> b = create(new DifferentFieldIdB().build());

        assertThat(isSame(a,b),is(false));
        assertThat(isSame(b,a),is(false));
    }

    @Test
    public void isDifferentForOnrMoreOperation() {
        ThreadIndexToElementList<InterleaveAction> a = create(new DifferentHashCodeA().build());
        ThreadIndexToElementList<InterleaveAction> b = create(new OneMoreOperation().build());

        assertThat(isSame(a,b),is(false));
        assertThat(isSame(b,a),is(false));

    }
}
