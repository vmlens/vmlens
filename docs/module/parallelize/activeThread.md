# Problem

Determine the active thread.
Based on calculated thread or always the current if no calculation exists or the thread was blocked.
Implemented in parallelize and interleave.

# Structure

parallelize:
    RunImpl locks
    WaitNotifyStrategyImpl wait
    ThreadIndexAndThreadStateMap all thread indices and there state

    RunStateMachineImpl isActive (threadIndex) -> InterleaveRun.isActive


# Scenarios and Tests

