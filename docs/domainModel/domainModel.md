# Problem

To test a java class we must handle all used abstractions of this class.
Abstractions include
    normal field access -> Data Race Detection
    Barriers
    Locks
    Volatile Fields
    Atomic Classes
    Synchronized Blocks
    Thread operations
    Fork/Join
    reflection for field access
    varhandle
    loops

ToDo ConditionWaitEnterEvent ConditionWaitExitEvent beschreiben
ToDo: Keys

# Steps

We need to detect an abstraction in the bytecode and record it

We need to have he possible to control the order of the threads at an abstraction

we need to be able to calculate the thread interleaving
we need to be able to enable, disable a call to a method -> wait spurious wakeup






