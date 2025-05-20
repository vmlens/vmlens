package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.{AtomicNonBlocking, AtomicNonBlockingMethod, Filter, PreAnalyzedList, Read, ReadWrite, Write}

object ConcurrentLinkedDequeFactory {

  def concurrentLinkedDeque(): PreAnalyzedList =
    PreAnalyzedList(List(AtomicNonBlocking("java/util/concurrent/ConcurrentLinkedDeque", atomicMethods()),
      Filter("java/util/concurrent/ConcurrentLinkedDeque$")
    ));


  private def atomicMethods(): List[AtomicNonBlockingMethod] = List[AtomicNonBlockingMethod](
    AtomicNonBlockingMethod("offerFirst","(Ljava/lang/Object;)Z",  Write()    ),
    AtomicNonBlockingMethod("isEmpty","()Z", Read()   ),
    AtomicNonBlockingMethod("getFirst","()Ljava/lang/Object;",  Read()   ),
    AtomicNonBlockingMethod("peek","()Ljava/lang/Object;",   Read()  ),
    AtomicNonBlockingMethod("pop","()Ljava/lang/Object;",  ReadWrite()  ),
    AtomicNonBlockingMethod("stream","()Ljava/util/stream/Stream;",  Read()  ),
    AtomicNonBlockingMethod("push","(Ljava/lang/Object;)V",  Write()   ),
    AtomicNonBlockingMethod("containsAll","(Ljava/util/Collection;)Z",  Read()  ),
    AtomicNonBlockingMethod("getLast","()Ljava/lang/Object;",  Read()  ),
    AtomicNonBlockingMethod("addLast","(Ljava/lang/Object;)V",   Write()   ),
    AtomicNonBlockingMethod("removeAll","(Ljava/util/Collection;)Z",  Write()   ),
    AtomicNonBlockingMethod("retainAll","(Ljava/util/Collection;)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("pollLast","()Ljava/lang/Object;",  ReadWrite() ),
    AtomicNonBlockingMethod("toArray","(Ljava/util/function/IntFunction;)[Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("addAll","(Ljava/util/Collection;)Z", Write()  ),
    AtomicNonBlockingMethod("remove","()Ljava/lang/Object;",  Write() ),
    AtomicNonBlockingMethod("iterator","()Ljava/util/Iterator;", Read()  ),
    AtomicNonBlockingMethod("clear","()V", Write()  ),
    AtomicNonBlockingMethod("pollFirst","()Ljava/lang/Object;", ReadWrite()  ),
    AtomicNonBlockingMethod("poll","()Ljava/lang/Object;", ReadWrite()  ),
    AtomicNonBlockingMethod("offer","(Ljava/lang/Object;)Z",  Write() ),
    AtomicNonBlockingMethod("add","(Ljava/lang/Object;)Z", Write()  ),
    AtomicNonBlockingMethod("addFirst","(Ljava/lang/Object;)V", Write()  ),
    AtomicNonBlockingMethod("toArray","()[Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("forEach","(Ljava/util/function/Consumer;)V", ReadWrite()  ),
    AtomicNonBlockingMethod("descendingIterator","()Ljava/util/Iterator;", ReadWrite()   ),
    AtomicNonBlockingMethod("equals","(Ljava/lang/Object;)Z", Read()  ),
    AtomicNonBlockingMethod("removeLastOccurrence","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("toArray","([Ljava/lang/Object;)[Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("peekFirst","()Ljava/lang/Object;",  ReadWrite() ),
    AtomicNonBlockingMethod("size","()I",  Read() ),
    AtomicNonBlockingMethod("element","()Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("removeIf","(Ljava/util/function/Predicate;)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("spliterator","()Ljava/util/Spliterator;", Read()  ),
    AtomicNonBlockingMethod("toString","()Ljava/lang/String;", Read()  ),
    AtomicNonBlockingMethod("remove","(Ljava/lang/Object;)Z",  ReadWrite()  ),
    AtomicNonBlockingMethod("removeFirstOccurrence","(Ljava/lang/Object;)Z", ReadWrite()  ),
    AtomicNonBlockingMethod("offerLast","(Ljava/lang/Object;)Z", Write()  ),
    AtomicNonBlockingMethod("removeFirst","()Ljava/lang/Object;",  ReadWrite()  ),
    AtomicNonBlockingMethod("peekLast","()Ljava/lang/Object;", Read()  ),
    AtomicNonBlockingMethod("parallelStream","()Ljava/util/stream/Stream;", Read()  ),
    AtomicNonBlockingMethod("removeLast","()Ljava/lang/Object;", ReadWrite()   ),
    AtomicNonBlockingMethod("contains","(Ljava/lang/Object;)Z",  Read() ),
    AtomicNonBlockingMethod("hashCode","()I",  Read() ) )
    
    
  

}
