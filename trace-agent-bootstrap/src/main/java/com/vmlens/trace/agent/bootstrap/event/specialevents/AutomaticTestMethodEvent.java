package com.vmlens.trace.agent.bootstrap.event.specialevents;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.AutomaticTestMethodEventGen;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

public class AutomaticTestMethodEvent extends AutomaticTestMethodEventGen implements SerializableEvent  {
    
    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setAutomaticTestType(int automaticTestType) {
        this.automaticTestType = automaticTestType;
    }

    public void setAutomaticTestId(int automaticTestId) {
        this.automaticTestId = automaticTestId;
    }

    public void setAutomaticTestMethodId(int automaticTestMethodId) {
        this.automaticTestMethodId = automaticTestMethodId;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(getStream(streamRepository).getStream());
    }
    
}
