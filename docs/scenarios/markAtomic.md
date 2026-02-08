# Problem


# Solution

# Events

Call Read -> Thread Id, RunId, new id
Call Write -> Thread Id, RunId, new id

atomic classname -> AtomicClassId
AtomicMethodId 


mark first method id
new: we need the call tree for this calls (root)

Event AtomicMethod
Thread Id, RunId, atomicMethodId, atomicClassId, atomicType: read, write...



Event AtomicTestSuccess
atomicClassId



AtomicDefinition
testatomic

as description
atomicClassId, classname


filter based on
atomicMethodId, atomicClassId


success if AtomicTestSuccess and all loops do not have a data race


besonderheit alles am anfang einlesen


# Process

Read Atomic
Create HashMap loopid, runid, threadId -> Graph?
which key?







