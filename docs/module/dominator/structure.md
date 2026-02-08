# Problem

method enter/exit
monitor/lock enter/exit

open AtomicNonBlockingEvent with callback?



# Solution

process all events sorted by one counter per thread similar to stack traces
build a graph for this
and calculate dominator tree
create a report
use only oner run per loop (check per hashmap already created?)

here we could use a map and check if dominator tree was already geneated
class ProcessEvents(private val loadRuns: LoadRuns, private val processRun: ProcessRun) {

def process() : Seq[LoopResult] = {

    val loopResultCollection = new LoopResultCollection(processRun.runContext.showAllRuns);
    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.build();
}

loopResultCollection.put(dominator tree)

first step based on stacktrace tree

# Vertex Types

method enter/exit
monitor or atomiclock enter/exit
Lock
Volatile or atomic non blocking
root
