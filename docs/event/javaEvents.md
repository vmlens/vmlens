SerializableEvent
EitherPluginEventOnlyOrInterleaveActionFactory


RuntimeEvent -> SerializableEvent, EitherPluginEventOnlyOrInterleaveActionFactory


PluginEventOnly -> RuntimeEvent
InterleaveActionFactoryAndRuntimeEvent -> RuntimeEvent , InterleaveActionFactory


ParallelizeActionMultiJoin -> EitherPluginEventOnlyOrInterleaveActionFactory, InterleaveActionFactory 

NotThreadOperationFactory ->    InterleaveActionFactoryAndRuntimeEvent
ExecuteBeforeEvent -> InterleaveActionFactoryAndRuntimeEvent
ThreadStart -> ExecuteBeforeEvent
ThreadJoin -> InterleaveActionFactoryAndRuntimeEvent