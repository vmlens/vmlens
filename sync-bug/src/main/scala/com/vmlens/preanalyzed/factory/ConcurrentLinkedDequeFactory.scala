package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicClassNonBlockingMethod, Filter, PreAnalyzedList, Read, ReadWrite, Write}

object ConcurrentLinkedDequeFactory {

  def concurrentLinkedDeque(): PreAnalyzedList =
    PreAnalyzedList(List(AtomicNonBlocking("java/util/concurrent/ConcurrentLinkedDeque", atomicMethods()),
      Filter("java/util/concurrent/ConcurrentLinkedDeque$")
    ));


  private def atomicMethods(): List[AtomicClassNonBlockingMethod] = List[AtomicClassNonBlockingMethod](
    AtomicClassNonBlockingMethod("offerFirst","(Ljava/lang/Object;)Z",  Write()    ),
    AtomicClassNonBlockingMethod("isEmpty","()Z", Read()   ),
    AtomicClassNonBlockingMethod("getFirst","()Ljava/lang/Object;",  Read()   ),
    AtomicClassNonBlockingMethod("peek","()Ljava/lang/Object;",   Read()  ),
    AtomicClassNonBlockingMethod("pop","()Ljava/lang/Object;",  ReadWrite()  ),
    AtomicClassNonBlockingMethod("stream","()Ljava/util/stream/Stream;",  Read()  ),
    AtomicClassNonBlockingMethod("push","(Ljava/lang/Object;)V",  Write()   ),
    AtomicClassNonBlockingMethod("containsAll","(Ljava/util/Collection;)Z",  Read()  ),
    AtomicClassNonBlockingMethod("getLast","()Ljava/lang/Object;",  Read()  ),
    AtomicClassNonBlockingMethod("addLast","(Ljava/lang/Object;)V",   Write()   ),
    AtomicClassNonBlockingMethod("removeAll","(Ljava/util/Collection;)Z",  Write()   ),
    AtomicClassNonBlockingMethod("retainAll","(Ljava/util/Collection;)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("pollLast","()Ljava/lang/Object;",  ReadWrite() ),
    AtomicClassNonBlockingMethod("toArray","(Ljava/util/function/IntFunction;)[Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("addAll","(Ljava/util/Collection;)Z", Write()  ),
    AtomicClassNonBlockingMethod("remove","()Ljava/lang/Object;",  Write() ),
    AtomicClassNonBlockingMethod("iterator","()Ljava/util/Iterator;", Read()  ),
    AtomicClassNonBlockingMethod("clear","()V", Write()  ),
    AtomicClassNonBlockingMethod("pollFirst","()Ljava/lang/Object;", ReadWrite()  ),
    AtomicClassNonBlockingMethod("poll","()Ljava/lang/Object;", ReadWrite()  ),
    AtomicClassNonBlockingMethod("offer","(Ljava/lang/Object;)Z",  Write() ),
    AtomicClassNonBlockingMethod("add","(Ljava/lang/Object;)Z", Write()  ),
    AtomicClassNonBlockingMethod("addFirst","(Ljava/lang/Object;)V", Write()  ),
    AtomicClassNonBlockingMethod("toArray","()[Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("forEach","(Ljava/util/function/Consumer;)V", ReadWrite()  ),
    AtomicClassNonBlockingMethod("descendingIterator","()Ljava/util/Iterator;", ReadWrite()   ),
    AtomicClassNonBlockingMethod("equals","(Ljava/lang/Object;)Z", Read()  ),
    AtomicClassNonBlockingMethod("removeLastOccurrence","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("toArray","([Ljava/lang/Object;)[Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("peekFirst","()Ljava/lang/Object;",  ReadWrite() ),
    AtomicClassNonBlockingMethod("size","()I",  Read() ),
    AtomicClassNonBlockingMethod("element","()Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("removeIf","(Ljava/util/function/Predicate;)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("spliterator","()Ljava/util/Spliterator;", Read()  ),
    AtomicClassNonBlockingMethod("toString","()Ljava/lang/String;", Read()  ),
    AtomicClassNonBlockingMethod("remove","(Ljava/lang/Object;)Z",  ReadWrite()  ),
    AtomicClassNonBlockingMethod("removeFirstOccurrence","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicClassNonBlockingMethod("offerLast","(Ljava/lang/Object;)Z", Write()  ),
    AtomicClassNonBlockingMethod("removeFirst","()Ljava/lang/Object;",  ReadWrite()  ),
    AtomicClassNonBlockingMethod("peekLast","()Ljava/lang/Object;", Read()  ),
    AtomicClassNonBlockingMethod("parallelStream","()Ljava/util/stream/Stream;", Read()  ),
    AtomicClassNonBlockingMethod("removeLast","()Ljava/lang/Object;", ReadWrite()   ),
    AtomicClassNonBlockingMethod("contains","(Ljava/lang/Object;)Z",  Read() ),
    AtomicClassNonBlockingMethod("hashCode","()I",  Read() ) )
    
    
  

}
