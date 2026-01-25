package com.vmlens.trace.agent.bootstrap.interleave.inttest.performance;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;

public class InterleaveActionLuceneRead extends AbstractInterleaveActionBuilder {
    @Override
    protected void addActions() {
        threadStart(0,1);
        threadStart(0,2);
        volatileAccess(1,atomic(315461968),3);
        volatileAccess(1,staticVolatile(182),1);
        volatileAccess(2,atomic(315461968),3);
        volatileAccess(1,staticVolatile(2268),1);
        volatileAccess(2,staticVolatile(182),1);
        lockEnter(1,monitor(532692882L));
        volatileAccess(2,staticVolatile(2268),1);
        lockExit(1,monitor(532692882L));
        lockEnter(2,monitor(1086718425L));
        lockEnter(1,monitor(532692882L));
        lockExit(2,monitor(1086718425L));
        lockExit(1,monitor(532692882L));
        lockEnter(2,monitor(1086718425L));
        lockEnter(1,monitor(532692882L));
        lockExit(2,monitor(1086718425L));
        lockExit(1,monitor(532692882L));
        lockEnter(2,monitor(1086718425L));
        lockEnter(1,monitor(566155887L));
        lockExit(2,monitor(1086718425L));
        volatileAccess(1,atomic(717058402),3);
        lockEnter(2,monitor(1664660872L));
        lockExit(1,monitor(566155887L));
        volatileAccess(2,atomic(1472108838),3);
        volatileAccess(1,atomic(1608392036),1);
        lockExit(2,monitor(1664660872L));
        volatileAccess(1,atomic(1608392036),1);
        volatileAccess(2,atomic(1608392036),1);
        volatileAccess(1,atomic(240259175),1);
        volatileAccess(2,atomic(1608392036),1);
        volatileAccess(1,volatileField(3706,1489870911L),1);
        volatileAccess(2,atomic(240259175),1);
        volatileAccess(1,volatileField(3706,1489870911L),1);
        volatileAccess(2,volatileField(3706,1489870911L),1);
        volatileAccess(1,atomic(1608392036),1);
        volatileAccess(2,volatileField(3706,1489870911L),1);
        volatileAccess(1,atomic(1608392036),1);
        volatileAccess(2,atomic(1608392036),1);
        volatileAccess(1,atomic(1608392036),1);
        volatileAccess(2,atomic(1608392036),1);
        volatileAccess(1,atomic(1608392036),1);
        volatileAccess(2,atomic(1608392036),1);
        lastActionInThread(1);
        volatileAccess(2,atomic(1608392036),1);
        threadJoin(0,1);
        lastActionInThread(2);
        threadJoin(0,2);
    }
}
