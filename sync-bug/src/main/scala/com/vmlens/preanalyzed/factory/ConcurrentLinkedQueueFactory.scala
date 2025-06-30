package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.classmodel.{AtomicNonBlocking, Filter}
import com.vmlens.preanalyzed.model.{AtomicClassNonBlockingMethod, PreAnalyzedList, Read, ReadWrite, Write}

object ConcurrentLinkedQueueFactory {

  def concurrentLinkedQueue(): PreAnalyzedList =
    PreAnalyzedList(List(AtomicNonBlocking("java/util/concurrent/ConcurrentLinkedQueue", atomicMethods()),
      Filter("java/util/concurrent/ConcurrentLinkedQueue$")
    ));


  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("isEmpty","()Z", Read()   ),
    AtomicClassNonBlockingMethod("peek","()Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("stream","()Ljava/util/stream/Stream;",  Read()  ),
    AtomicClassNonBlockingMethod("containsAll","(Ljava/util/Collection;)Z", Read()   ),
    AtomicClassNonBlockingMethod("removeAll","(Ljava/util/Collection;)Z",  ReadWrite()   ),
    AtomicClassNonBlockingMethod("retainAll","(Ljava/util/Collection;)Z",  ReadWrite()   ),
    AtomicClassNonBlockingMethod("toArray","(Ljava/util/function/IntFunction;)[Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("addAll","(Ljava/util/Collection;)Z",    Write()  ),
    AtomicClassNonBlockingMethod("remove","()Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("iterator","()Ljava/util/Iterator;",Read()    ),
    AtomicClassNonBlockingMethod("clear","()V",   ReadWrite()  ),
    AtomicClassNonBlockingMethod("poll","()Ljava/lang/Object;",  ReadWrite()   ),
    AtomicClassNonBlockingMethod("offer","(Ljava/lang/Object;)Z",   Write()   ),
    AtomicClassNonBlockingMethod("add","(Ljava/lang/Object;)Z",   Write()   ),
    AtomicClassNonBlockingMethod("toArray","()[Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("forEach","(Ljava/util/function/Consumer;)V", Read()   ),
    AtomicClassNonBlockingMethod("equals","(Ljava/lang/Object;)Z",  Read()  ),
    AtomicClassNonBlockingMethod("toArray","([Ljava/lang/Object;)[Ljava/lang/Object;",  Read()  ),
    AtomicClassNonBlockingMethod("size","()I", Read()   ),
    AtomicClassNonBlockingMethod("element","()Ljava/lang/Object;", Read()   ),
    AtomicClassNonBlockingMethod("removeIf","(Ljava/util/function/Predicate;)Z",  ReadWrite() ),
    AtomicClassNonBlockingMethod("spliterator","()Ljava/util/Spliterator;", Read()   ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read()   ),
    AtomicClassNonBlockingMethod("remove","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("parallelStream","()Ljava/util/stream/Stream;", Read()   ),
    AtomicClassNonBlockingMethod("contains","(Ljava/lang/Object;)Z", Read()   ),
    AtomicClassNonBlockingMethod("hashCode","()I", Read()   )
  )


}
