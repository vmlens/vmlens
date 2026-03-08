package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{COUNT_DOWN_LATCH_COUNT_DOWN, COUNT_DOWN_LATCH_AWAIT, COUNT_DOWN_LATCH_GET_STATE}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod

object CountDownLatchFactory {


  def countDownLatchFactory(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/CountDownLatch", methods(), List())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("await", "()V", COUNT_DOWN_LATCH_AWAIT),
    new MethodToMethodType("countDown", "()V", COUNT_DOWN_LATCH_COUNT_DOWN),
    new MethodToMethodType("getCount", "()J",   COUNT_DOWN_LATCH_GET_STATE ),
    new MethodToMethodType("await", "(JLjava/util/concurrent/TimeUnit;)Z",  COUNT_DOWN_LATCH_AWAIT  ),
    new MethodToMethodType("toString", "()Ljava/lang/String;", COUNT_DOWN_LATCH_GET_STATE   )
  );

}
