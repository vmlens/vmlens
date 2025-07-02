package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{CONDITION_AWAIT, FUTURE_GET, FUTURE_SET, FUTURE_RUN}


object FutureFactory {

  def futureTask(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/util/concurrent/FutureTask", methods())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "()Ljava/lang/Object;", FUTURE_GET),
    new MethodToMethodType("get", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", FUTURE_GET),
    new MethodToMethodType("set", "(Ljava/lang/Object;)V", FUTURE_SET),
    new MethodToMethodType( "run", "()V", FUTURE_RUN),


  );


}
