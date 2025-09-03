# Problem

We have multiple method calls which all have some requirements in common:
Filter and Stacktrace Calculation
Mocking of the command processing

We therefore implement the method calls as commands.

# Data Types

CallbackAction : DoNotTraceIn or CallStrategy
CallStrategy : [X]Action

For example: BeforeFieldAccessAction, AfterFieldAccessAction


# Dataflow

Callback -> new CallbackAction[X] -> 
CallbackActionProcessor(callbackAction) -> doubleDispatch( callbackAction , strategy )

# Structure

Callback ->  CallbackActionProcessor
Callback[X] -> Context[X]
CallbackAction[X] -> Context[X]

# Scenarios

first dimension callback call
second dimension strategy

array access
field Access
method enter/exit
pre analyzed
    methodEnter
    methodEnterWithIntParam
    methodExit
    methodExitObjectReturn
thread pool
    join
        setInThreadPool(true);
        wird das überhaupt verwendet?
    start
        boolean return
    joinExit
        setInThreadPool(false);
        wird das überhaupt verwendet?
vmlens api
wait notify









