package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

/**
 * this either lock exit or wait.
 * Since this behaves always the same this is only a position
 */

public class BlockEnd {

    private final Position position;

    public BlockEnd(Position position) {
        this.position = position;
    }

    public Position position() {
        return position;
    }
}
