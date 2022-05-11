package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;

/**
 * Represents one AllInterleavingsLoop.
 */

public class AllInterleavingsLoop {
    private final AllInterleavings allInterleavings;
    private final int loopId;
    private int maxRunId;

    private volatile AllInterleavingsRun currentRun;

    public AllInterleavingsLoop(AllInterleavings allInterleavings, int loopId) {
        this.allInterleavings = allInterleavings;
        this.loopId = loopId;
    }

    public boolean advance() {
        AllInterleavingsRun temp = currentRun;
        if (temp == null) {
            return false;
        }
        boolean advance = temp.advance();
        if (advance) {
            currentRun = new AllInterleavingsRun(maxRunId++); // Fixme Thread Safety
            return true;
        }

        return false;
    }

    public void close() {
        AllInterleavingsRun temp = currentRun;
        if (temp == null) {
            return;
        }
        temp.close();
        currentRun = null;
    }

    public void after(Command command, long threadId) {
        AllInterleavingsRun temp = currentRun;
        if (temp == null) {
            return;
        }
        temp.after(command, threadId);
    }

    public boolean waitsForThreadStart() {

    }


}
