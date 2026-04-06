# Problem

Test builder for each possible atomic, lineralizable class. Types of classes:

* Atomic, for example queue, Counter..
* Atomic with key: for example hashmap
* Multiple Threads to single Thread or vice veras for example MPSC
* Scheduling
* Barriers, Futures
* Locks

Problem is also how many threads do we need for a test?
What combinations do we need to test, for example ABA Problem by queues.

# Solution

We use a builder for Atomic and Atomic classes with keys. The other cases will be implemented later.
We use an api to serialize other types for example locks and barriers without test.

The test builder can be configured by the number of threads and number of methods called per thread to 
analyze who manay threads we need and if there are cases where we need more than 3 or 4 threads
Also how many method types are called in parallel.


# Structure

AtomicTestBuilder -> AutomaticTestImpl -> { Recording, TestRunner }

ConsumerWrapper
FunctionAndCompare -> FunctionCompareAndExpectedResult

# Distribute on Threads

The Size+1 gives the length of the list and also the maximum number of threads 
For Size 1 we have two elements so 2 Threads

If we have more than two threads we can filter test runs
ABA = BAA  if we have three threads
For this we use a set of frequency maps

# Dataflow

createClassUnderTest,writeList,readOnlyList,atomicTestId,size ->
writeList,readOnlyList -> RecordFactory




