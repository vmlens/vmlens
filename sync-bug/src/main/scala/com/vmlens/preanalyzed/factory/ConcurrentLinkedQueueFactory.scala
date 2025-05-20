package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, Filter, PreAnalyzedList, Read, ReadWrite, Write}

object ConcurrentLinkedQueueFactory {

  def concurrentLinkedQueue(): PreAnalyzedList =
    PreAnalyzedList(List(AtomicNonBlocking("java/util/concurrent/ConcurrentLinkedQueue", atomicMethods()),
      Filter("java/util/concurrent/ConcurrentLinkedQueue$")
    ));


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("isEmpty","()Z", Read()   ),
    AtomicNonBlockingMethod("peek","()Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("stream","()Ljava/util/stream/Stream;",  Read()  ),
    AtomicNonBlockingMethod("containsAll","(Ljava/util/Collection;)Z", Read()   ),
    AtomicNonBlockingMethod("removeAll","(Ljava/util/Collection;)Z",  ReadWrite()   ),
    AtomicNonBlockingMethod("retainAll","(Ljava/util/Collection;)Z",  ReadWrite()   ),
    AtomicNonBlockingMethod("toArray","(Ljava/util/function/IntFunction;)[Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("addAll","(Ljava/util/Collection;)Z",    Write()  ),
    AtomicNonBlockingMethod("remove","()Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("iterator","()Ljava/util/Iterator;",Read()    ),
    AtomicNonBlockingMethod("clear","()V",   ReadWrite()  ),
    AtomicNonBlockingMethod("poll","()Ljava/lang/Object;",  ReadWrite()   ),
    AtomicNonBlockingMethod("offer","(Ljava/lang/Object;)Z",   Write()   ),
    AtomicNonBlockingMethod("add","(Ljava/lang/Object;)Z",   Write()   ),
    AtomicNonBlockingMethod("toArray","()[Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("forEach","(Ljava/util/function/Consumer;)V", Read()   ),
    AtomicNonBlockingMethod("equals","(Ljava/lang/Object;)Z",  Read()  ),
    AtomicNonBlockingMethod("toArray","([Ljava/lang/Object;)[Ljava/lang/Object;",  Read()  ),
    AtomicNonBlockingMethod("size","()I", Read()   ),
    AtomicNonBlockingMethod("element","()Ljava/lang/Object;", Read()   ),
    AtomicNonBlockingMethod("removeIf","(Ljava/util/function/Predicate;)Z",  ReadWrite() ),
    AtomicNonBlockingMethod("spliterator","()Ljava/util/Spliterator;", Read()   ),
    AtomicNonBlockingMethod("toString","()Ljava/lang/String;", Read()   ),
    AtomicNonBlockingMethod("remove","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("parallelStream","()Ljava/util/stream/Stream;", Read()   ),
    AtomicNonBlockingMethod("contains","(Ljava/lang/Object;)Z", Read()   ),
    AtomicNonBlockingMethod("hashCode","()I", Read()   )
  )


}
