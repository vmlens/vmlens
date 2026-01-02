# Problem

PreAnalyzed
Normal
Filter
DoNotTraceIn
AllInterleavings
Thread pools
Fork join pool

# Structure Usage


ClassFilterAndTransformerStrategy -> TransformerStrategy

TransformerStrategy <- TransformerStrategyForClassTransformer  : implements

TransformerStrategyForClassTransformer -> FactoryCollectionFactory

FactoryCollectionFactory <- FactoryCollectionThreadPoolFactory : implements

# Structure Creation

ClassTransformerListBuilderImpl -> ClassFilterAndTransformerStrategy : build

special method for FactoryCollectionThreadPoolFactory

decision on method enter / exit strategy for preanalyzed based on Strategy