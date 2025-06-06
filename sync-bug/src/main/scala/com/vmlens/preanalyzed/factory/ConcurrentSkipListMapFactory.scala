package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, Filter, PreAnalyzedList, Read, ReadWrite}

object ConcurrentSkipListMapFactory {

  def concurrentSkipListMap(): PreAnalyzedList = {
    PreAnalyzedList(List( 
      AtomicNonBlocking("java/util/concurrent/ConcurrentSkipListMap", concurrentMapMethods()),
      Filter("java/util/concurrent/ConcurrentSkipListMap$")
    ))

  }


  private def concurrentMapMethods(): List[AtomicClassNonBlockingMethod] =
    List[AtomicClassNonBlockingMethod](
      AtomicClassNonBlockingMethod("keySet", "()Ljava/util/Set;", Read()),
      AtomicClassNonBlockingMethod("isEmpty", "()Z", Read()),
      AtomicClassNonBlockingMethod("containsValue", "(Ljava/lang/Object;)Z", Read()),
      AtomicClassNonBlockingMethod("values", "()Ljava/util/Collection;", Read()),
      AtomicClassNonBlockingMethod("containsKey", "(Ljava/lang/Object;)Z", Read()),
      AtomicClassNonBlockingMethod("computeIfPresent", "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("clear", "()V", ReadWrite()),
      AtomicClassNonBlockingMethod("putAll", "(Ljava/util/Map;)V", ReadWrite()),
      AtomicClassNonBlockingMethod("equals", "(Ljava/lang/Object;)Z", Read()),
      AtomicClassNonBlockingMethod("remove", "(Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("size", "()I", Read()),
      AtomicClassNonBlockingMethod("putIfAbsent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicClassNonBlockingMethod("computeIfAbsent", "(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("hashCode", "()I", Read()),
      AtomicClassNonBlockingMethod("remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", ReadWrite()),
      AtomicClassNonBlockingMethod("merge", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("forEach", "(Ljava/util/function/BiConsumer;)V", Read()),
      AtomicClassNonBlockingMethod("replace", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", ReadWrite()),
      AtomicClassNonBlockingMethod("entrySet", "()Ljava/util/Set;", Read()),
      AtomicClassNonBlockingMethod("compute", "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("replace", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("replaceAll", "(Ljava/util/function/BiFunction;)V", ReadWrite()),
      AtomicClassNonBlockingMethod("toString", "()Ljava/lang/String;", Read()),
      AtomicClassNonBlockingMethod("get", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicClassNonBlockingMethod("put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicClassNonBlockingMethod("headMap", "(Ljava/lang/Object;Z)Ljava/util/NavigableMap;", Read()),
      AtomicClassNonBlockingMethod("higherKey", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicClassNonBlockingMethod("floorEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", Read()),
      AtomicClassNonBlockingMethod("subMap", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;", Read()),
      AtomicClassNonBlockingMethod("floorKey", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicClassNonBlockingMethod("headMap", "(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicClassNonBlockingMethod("descendingMap", "()Ljava/util/NavigableMap;",Read()),
      AtomicClassNonBlockingMethod("pollLastEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("subMap", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicClassNonBlockingMethod("subMap", "(Ljava/lang/Object;ZLjava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicClassNonBlockingMethod("clone", "()Ljava/util/concurrent/ConcurrentSkipListMap;",Read()),
      AtomicClassNonBlockingMethod("ceilingKey", "(Ljava/lang/Object;)Ljava/lang/Object;",Read()),
      AtomicClassNonBlockingMethod("headMap", "(Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicClassNonBlockingMethod("tailMap", "(Ljava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicClassNonBlockingMethod("tailMap", "(Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicClassNonBlockingMethod("ceilingEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("comparator", "()Ljava/util/Comparator;",Read()),
      AtomicClassNonBlockingMethod("clone", "()Ljava/lang/Object;",Read()),
      AtomicClassNonBlockingMethod("pollFirstEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("higherEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("firstKey", "()Ljava/lang/Object;",Read()),
      AtomicClassNonBlockingMethod("navigableKeySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicClassNonBlockingMethod("tailMap", "(Ljava/lang/Object;Z)Ljava/util/NavigableMap;",Read()),
      AtomicClassNonBlockingMethod("descendingMap", "()Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicClassNonBlockingMethod("lastKey", "()Ljava/lang/Object;",Read()),
      AtomicClassNonBlockingMethod("subMap", "(Ljava/lang/Object;ZLjava/lang/Object;Z)Ljava/util/NavigableMap;",Read()),
      AtomicClassNonBlockingMethod("lowerKey", "(Ljava/lang/Object;)Ljava/lang/Object;",Read()),
      AtomicClassNonBlockingMethod("tailMap", "(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicClassNonBlockingMethod("firstEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("lastEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("lowerEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicClassNonBlockingMethod("descendingKeySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicClassNonBlockingMethod("keySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicClassNonBlockingMethod("headMap", "(Ljava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read())
      )

}
