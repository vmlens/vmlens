# Problem

PreAnalyzed needs sometimes different parameters for the methodEnter 
and exit.
All and DoNotTrace Not


# Structure

MethodEnterExitTransform -> MethodEnterExitStrategy -> {  NormalMethod , StaticMethodJvmAtLeast_1_5 , StaticMethodNoOp  } 
-> MethodCallbackFactory -> {  MethodCallbackFactoryAll , MethodCallbackFactoryPreAnalyzed }

MethodCallbackFactoryPreAnalyzed -> StrategyEnter, StrategyExit


## Responsibilities

MethodEnterExitStrategy responsible for if a call should be created and creation of callee
MethodCallbackFactory -> Callback Name and if special calls should be created

MethodCallbackFactoryPreAnalyzed -> Strategy, here can be special calls be created using strategies

# Creation MethodCallbackFactoryPreAnalyzed 

based on the pre analyzed strategy see FactoryCollectionPreAnalyzed using
MethodTransformerBuilderImpl 

