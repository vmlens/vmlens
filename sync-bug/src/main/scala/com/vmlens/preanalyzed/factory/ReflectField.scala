package com.vmlens.preanalyzed.factory

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{REFLECT_FIELD_GET, REFLECT_FIELD_SET};


object ReflectField {
  
  def reflectField(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/lang/reflect/Field", methods())
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)Ljava/lang/Object;", REFLECT_FIELD_GET),
    new MethodToMethodType("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", REFLECT_FIELD_SET));

}
