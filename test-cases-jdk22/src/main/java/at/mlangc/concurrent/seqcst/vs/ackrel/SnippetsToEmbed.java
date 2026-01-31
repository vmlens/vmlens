package at.mlangc.concurrent.seqcst.vs.ackrel;

import java.util.concurrent.atomic.AtomicBoolean;

class SnippetsToEmbed {
    static class State {

    }

    State state;

    final AtomicBoolean initialized = new AtomicBoolean(false);
    void threadA() {
        state = new State();
        initialized.setRelease(true);
    }

    void otherThreads() {
        if (initialized.getAcquire()) {
            // Can safely assume that state is initialized
            assert state != null;
        }
    }
}
