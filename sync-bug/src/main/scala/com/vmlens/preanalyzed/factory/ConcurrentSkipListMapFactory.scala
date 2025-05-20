package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, Filter, PreAnalyzedList, Read, ReadWrite}

object ConcurrentSkipListMapFactory {

  def concurrentSkipListMap(): PreAnalyzedList = {
    PreAnalyzedList(List( 
      AtomicNonBlocking("java/util/concurrent/ConcurrentSkipListMap", concurrentMapMethods()),
      Filter("java/util/concurrent/ConcurrentSkipListMap$")
    ))

  }


  private def concurrentMapMethods(): List[AtomicNonBlockingMethod] =
    List[AtomicNonBlockingMethod](
      AtomicNonBlockingMethod("keySet", "()Ljava/util/Set;", Read()),
      AtomicNonBlockingMethod("isEmpty", "()Z", Read()),
      AtomicNonBlockingMethod("containsValue", "(Ljava/lang/Object;)Z", Read()),
      AtomicNonBlockingMethod("values", "()Ljava/util/Collection;", Read()),
      AtomicNonBlockingMethod("containsKey", "(Ljava/lang/Object;)Z", Read()),
      AtomicNonBlockingMethod("computeIfPresent", "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("clear", "()V", ReadWrite()),
      AtomicNonBlockingMethod("putAll", "(Ljava/util/Map;)V", ReadWrite()),
      AtomicNonBlockingMethod("equals", "(Ljava/lang/Object;)Z", Read()),
      AtomicNonBlockingMethod("remove", "(Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("size", "()I", Read()),
      AtomicNonBlockingMethod("putIfAbsent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicNonBlockingMethod("computeIfAbsent", "(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("hashCode", "()I", Read()),
      AtomicNonBlockingMethod("remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", ReadWrite()),
      AtomicNonBlockingMethod("merge", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("forEach", "(Ljava/util/function/BiConsumer;)V", Read()),
      AtomicNonBlockingMethod("replace", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", ReadWrite()),
      AtomicNonBlockingMethod("entrySet", "()Ljava/util/Set;", Read()),
      AtomicNonBlockingMethod("compute", "(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("replace", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("replaceAll", "(Ljava/util/function/BiFunction;)V", ReadWrite()),
      AtomicNonBlockingMethod("toString", "()Ljava/lang/String;", Read()),
      AtomicNonBlockingMethod("get", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicNonBlockingMethod("put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ReadWrite()),
      AtomicNonBlockingMethod("headMap", "(Ljava/lang/Object;Z)Ljava/util/NavigableMap;", Read()),
      AtomicNonBlockingMethod("higherKey", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicNonBlockingMethod("floorEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", Read()),
      AtomicNonBlockingMethod("subMap", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;", Read()),
      AtomicNonBlockingMethod("floorKey", "(Ljava/lang/Object;)Ljava/lang/Object;", Read()),
      AtomicNonBlockingMethod("headMap", "(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicNonBlockingMethod("descendingMap", "()Ljava/util/NavigableMap;",Read()),
      AtomicNonBlockingMethod("pollLastEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("subMap", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicNonBlockingMethod("subMap", "(Ljava/lang/Object;ZLjava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicNonBlockingMethod("clone", "()Ljava/util/concurrent/ConcurrentSkipListMap;",Read()),
      AtomicNonBlockingMethod("ceilingKey", "(Ljava/lang/Object;)Ljava/lang/Object;",Read()),
      AtomicNonBlockingMethod("headMap", "(Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicNonBlockingMethod("tailMap", "(Ljava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicNonBlockingMethod("tailMap", "(Ljava/lang/Object;)Ljava/util/SortedMap;",Read()),
      AtomicNonBlockingMethod("ceilingEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("comparator", "()Ljava/util/Comparator;",Read()),
      AtomicNonBlockingMethod("clone", "()Ljava/lang/Object;",Read()),
      AtomicNonBlockingMethod("pollFirstEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("higherEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("firstKey", "()Ljava/lang/Object;",Read()),
      AtomicNonBlockingMethod("navigableKeySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicNonBlockingMethod("tailMap", "(Ljava/lang/Object;Z)Ljava/util/NavigableMap;",Read()),
      AtomicNonBlockingMethod("descendingMap", "()Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicNonBlockingMethod("lastKey", "()Ljava/lang/Object;",Read()),
      AtomicNonBlockingMethod("subMap", "(Ljava/lang/Object;ZLjava/lang/Object;Z)Ljava/util/NavigableMap;",Read()),
      AtomicNonBlockingMethod("lowerKey", "(Ljava/lang/Object;)Ljava/lang/Object;",Read()),
      AtomicNonBlockingMethod("tailMap", "(Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentNavigableMap;",Read()),
      AtomicNonBlockingMethod("firstEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("lastEntry", "()Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("lowerEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;",Read()),
      AtomicNonBlockingMethod("descendingKeySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicNonBlockingMethod("keySet", "()Ljava/util/NavigableSet;",Read()),
      AtomicNonBlockingMethod("headMap", "(Ljava/lang/Object;Z)Ljava/util/concurrent/ConcurrentNavigableMap;",Read())
      )

}
