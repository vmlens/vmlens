package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.NEW_UPDATER


object AtomicIntegerFieldUpdater {

  def atomicIntegerFieldUpdater(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/atomic/AtomicIntegerFieldUpdater", methods())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("newUpdater", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", NEW_UPDATER)
  );


}
