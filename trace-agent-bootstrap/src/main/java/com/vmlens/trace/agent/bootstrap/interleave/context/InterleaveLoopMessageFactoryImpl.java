package com.vmlens.trace.agent.bootstrap.interleave.context;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;

public class InterleaveLoopMessageFactoryImpl implements InterleaveLoopMessageFactory {

    private final QueueIn queueIn;
    private final int loopId;

    public InterleaveLoopMessageFactoryImpl(QueueIn queueIn,
                                            int loopId) {
        this.queueIn = queueIn;
        this.loopId = loopId;
    }

    @Override
    public void cyclesRemoved(int actual) {
        sendMessage(LoopWarningEvent.cyclesRemoved(actual));
    }

    @Override
    public void maximumIterationsReached(int orderTreeLength) {
        sendMessage(LoopWarningEvent.maximumIterationsReached(orderTreeLength));

    }

    private void sendMessage(LoopWarningEvent message) {
        message.setLoopId(loopId);
        // runid is 0
        queueIn.offer(message);
    }

}
