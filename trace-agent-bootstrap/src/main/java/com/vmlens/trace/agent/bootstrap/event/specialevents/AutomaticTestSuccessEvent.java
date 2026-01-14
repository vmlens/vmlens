package com.vmlens.trace.agent.bootstrap.event.specialevents;

import com.vmlens.trace.agent.bootstrap.event.DummyLoopIds;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.AutomaticTestSuccessEventGen;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

public class AutomaticTestSuccessEvent extends AutomaticTestSuccessEventGen implements SerializableEvent {

    public AutomaticTestSuccessEvent() {
        this.runId = DummyLoopIds.DUMMY_RUN_ID;
        this.loopId = DummyLoopIds.DUMMY_LOOP_ID;
    }

    public void setAutomaticTestId(int automaticTestId) {
        this.automaticTestId = automaticTestId;
    }



    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(getStream(streamRepository).getStream());
    }
}
