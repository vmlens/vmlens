package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.exception.Message;

public class SendEvent {

    private final QueueIn queueIn;
    private final Run run;


    public SendEvent(QueueIn queueIn, Run run) {
        this.queueIn = queueIn;
        this.run = run;
    }

    public static SendEvent create(AfterContext afterContext, Run run) {
        return new SendEvent(afterContext.queueIn(),run);
    }

    public static SendEvent create(NewTaskContext newTaskContext, Run run) {
        return new SendEvent(newTaskContext.queueIn(),run);
    }

    public void sendSerializable(SerializableEvent serializableEvent) {
        queueIn.offer(serializableEvent);
    }

    public void sendMessage(Message message) {
        queueIn.offer(new LoopWarningEvent(run.loopId(), run.runId(), message.id()));
    }

}
