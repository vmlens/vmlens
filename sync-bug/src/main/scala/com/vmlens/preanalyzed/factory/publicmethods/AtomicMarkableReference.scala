package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.model.{AtomicClassNonBlockingMethod, MethodToMethodType, NotYetImplemented, Read, ReadWrite, Write}
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{NON_BLOCKING_READ_WRITE, NON_BLOCKING_READ, NON_BLOCKING_WRITE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl. NotYetImplementedMethod

object AtomicMarkableReference {

  def atomicMarkableReference(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicMarkableReference", methods(), List())
  }
  
  
  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("attemptMark","(Ljava/lang/Object;Z)Z",  NON_BLOCKING_READ_WRITE ),
    new MethodToMethodType("getReference","()Ljava/lang/Object;", NON_BLOCKING_READ ),
    new MethodToMethodType("get","([Z)Ljava/lang/Object;", NON_BLOCKING_READ   ),
    new MethodToMethodType("set","(Ljava/lang/Object;Z)V", NON_BLOCKING_WRITE  ),
    new MethodToMethodType("compareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",NON_BLOCKING_READ_WRITE  ),
    new MethodToMethodType("isMarked","()Z", NON_BLOCKING_READ   ),
    new MethodToMethodType("weakCompareAndSet","(Ljava/lang/Object;Ljava/lang/Object;ZZ)Z",   NotYetImplementedMethod.SINGLETON  )
  );
  

}
