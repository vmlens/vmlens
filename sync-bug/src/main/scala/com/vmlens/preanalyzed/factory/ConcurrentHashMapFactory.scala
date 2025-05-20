package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model._

object ConcurrentHashMapFactory {

  def concurrentHashMap(): PreAnalyzedList = {
    PreAnalyzedList(List(
      AtomicReadWriteLock("java/util/concurrent/ConcurrentHashMap", concurrentHashMapMethods()),
      Filter("java/util/concurrent/ConcurrentHashMap")
    ))
  }

  private def concurrentHashMapMethods(): List[MethodWithLock] =
    List[MethodWithLock](
      MethodWithLock("reduceEntriesToInt","(JLjava/util/function/ToIntFunction;ILjava/util/function/IntBinaryOperator;)I", WriteLock()  ),
        MethodWithLock("keySet","()Ljava/util/Set;", ReadLock()  ),
        MethodWithLock("isEmpty","()Z", ReadLock()  ),
        MethodWithLock("containsValue","(Ljava/lang/Object;)Z", ReadLock()  ),
        MethodWithLock("reduceEntriesToLong","(JLjava/util/function/ToLongFunction;JLjava/util/function/LongBinaryOperator;)J",  ReadLock()  ),
        MethodWithLock("values","()Ljava/util/Collection;", ReadLock()  ),
        MethodWithLock("reduceValues","(JLjava/util/function/Function;Ljava/util/function/BiFunction;)Ljava/lang/Object;",   ReadLock()),
        MethodWithLock("forEachEntry","(JLjava/util/function/Function;Ljava/util/function/Consumer;)V", ReadLock()  ),
        MethodWithLock("forEach","(JLjava/util/function/BiFunction;Ljava/util/function/Consumer;)V", ReadLock()  ),
        MethodWithLock("reduceValuesToDouble","(JLjava/util/function/ToDoubleFunction;DLjava/util/function/DoubleBinaryOperator;)D",  ReadLock() ),
        MethodWithLock("reduceValues","(JLjava/util/function/BiFunction;)Ljava/lang/Object;",  ReadLock()  ),
        MethodWithLock("forEachEntry","(JLjava/util/function/Consumer;)V",   ReadLock()  ),
        MethodWithLock("searchEntries","(JLjava/util/function/Function;)Ljava/lang/Object;",   ReadLock()  ),
        MethodWithLock("containsKey","(Ljava/lang/Object;)Z",   ReadLock()  ),
        MethodWithLock("keySet","(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentHashMap$KeySetView;",   ReadLock()  ),
        MethodWithLock("computeIfPresent","(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", WriteLock()  ),
        MethodWithLock("clear","()V",  WriteLock() ),
        MethodWithLock("forEach","(JLjava/util/function/BiConsumer;)V",   ReadLock()  ),
        MethodWithLock("reduceToInt","(JLjava/util/function/ToIntBiFunction;ILjava/util/function/IntBinaryOperator;)I",  ReadLock()  ),
        MethodWithLock("putAll","(Ljava/util/Map;)V",  WriteLock()  ),
        MethodWithLock("equals","(Ljava/lang/Object;)Z",   ReadLock()  ),
        MethodWithLock("reduce","(JLjava/util/function/BiFunction;Ljava/util/function/BiFunction;)Ljava/lang/Object;",  ReadLock()  ),
        MethodWithLock("remove","(Ljava/lang/Object;)Ljava/lang/Object;",  WriteLock()  ),
        MethodWithLock("keySet","()Ljava/util/concurrent/ConcurrentHashMap$KeySetView;",   ReadLock()  ),
        MethodWithLock("size","()I",  ReadLock()  ),
        MethodWithLock("putIfAbsent","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", WriteLock()  ),
        MethodWithLock("getOrDefault","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  ReadLock()  ),
        MethodWithLock("computeIfAbsent","(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", WriteLock()   ),
        MethodWithLock("searchValues","(JLjava/util/function/Function;)Ljava/lang/Object;",  ReadLock()  ),
        MethodWithLock("hashCode","()I",  ReadLock()  ),
        MethodWithLock("search","(JLjava/util/function/BiFunction;)Ljava/lang/Object;",  ReadLock()  ),
        MethodWithLock("remove","(Ljava/lang/Object;Ljava/lang/Object;)Z", WriteLock()   ),
        MethodWithLock("reduceKeysToInt","(JLjava/util/function/ToIntFunction;ILjava/util/function/IntBinaryOperator;)I",  ReadLock()   ),
        MethodWithLock("reduceToDouble","(JLjava/util/function/ToDoubleBiFunction;DLjava/util/function/DoubleBinaryOperator;)D",  ReadLock()   ),
        MethodWithLock("keys","()Ljava/util/Enumeration;",  ReadLock()   ),
        MethodWithLock("forEachKey","(JLjava/util/function/Consumer;)V",   ReadLock()  ),
        MethodWithLock("merge","(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;",  WriteLock()   ),
        MethodWithLock("searchKeys","(JLjava/util/function/Function;)Ljava/lang/Object;",  ReadLock() ),
        MethodWithLock("forEach","(Ljava/util/function/BiConsumer;)V",  ReadLock()  ),
        MethodWithLock("reduceToLong","(JLjava/util/function/ToLongBiFunction;JLjava/util/function/LongBinaryOperator;)J",  ReadLock()  ),
        MethodWithLock("elements","()Ljava/util/Enumeration;",  ReadLock()  ),
        MethodWithLock("replace","(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z",   WriteLock() ),
        MethodWithLock("entrySet","()Ljava/util/Set;",  ReadLock() ),
        MethodWithLock("compute","(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;",  WriteLock() ),
        MethodWithLock("reduceValuesToLong","(JLjava/util/function/ToLongFunction;JLjava/util/function/LongBinaryOperator;)J",  ReadLock()  ),
        MethodWithLock("reduceEntries","(JLjava/util/function/BiFunction;)Ljava/util/Map$Entry;",  ReadLock()  ),
        MethodWithLock("reduceKeys","(JLjava/util/function/BiFunction;)Ljava/lang/Object;", ReadLock()   ),
        MethodWithLock("mappingCount","()J", ReadLock()   ),
        MethodWithLock("forEachValue","(JLjava/util/function/Consumer;)V", ReadLock()   ),
        MethodWithLock("replace","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",  WriteLock()  ),
        MethodWithLock("reduceValuesToInt","(JLjava/util/function/ToIntFunction;ILjava/util/function/IntBinaryOperator;)I", ReadLock()  ),
        MethodWithLock("forEachValue","(JLjava/util/function/Function;Ljava/util/function/Consumer;)V", ReadLock()  ),
        MethodWithLock("forEachKey","(JLjava/util/function/Function;Ljava/util/function/Consumer;)V", ReadLock()  ),
        MethodWithLock("replaceAll","(Ljava/util/function/BiFunction;)V", WriteLock()  ),
        MethodWithLock("reduceKeysToLong","(JLjava/util/function/ToLongFunction;JLjava/util/function/LongBinaryOperator;)J", ReadLock()  ),
        MethodWithLock("reduceEntries","(JLjava/util/function/Function;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadLock()  ),
        MethodWithLock("reduceKeysToDouble","(JLjava/util/function/ToDoubleFunction;DLjava/util/function/DoubleBinaryOperator;)D", ReadLock()  ),
        MethodWithLock("reduceKeys","(JLjava/util/function/Function;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadLock()  ),
        MethodWithLock("reduceEntriesToDouble","(JLjava/util/function/ToDoubleFunction;DLjava/util/function/DoubleBinaryOperator;)D", ReadLock()  ),
        MethodWithLock("toString","()Ljava/lang/String;", ReadLock()   ),
        MethodWithLock("contains","(Ljava/lang/Object;)Z",ReadLock()   ),
        MethodWithLock("get", "(Ljava/lang/Object;)Ljava/lang/Object;",ReadLock()),
        MethodWithLock("put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",WriteLock())
      
    )
  
}
