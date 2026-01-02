package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{REFLECT_FIELD_GET, REFLECT_FIELD_SET};


object ReflectField {
  
  def reflectField(): ClassWithMethodToMethodType = {
    new ClassWithMethodToMethodType("java/lang/reflect/Field", methods(),List("hashCode",
      "getAnnotatedType","getDeclaredAnnotations","getAnnotationsByType","getAnnotation","isSynthetic","getType","getDeclaringClass",
      "getModifiers","toGenericString","setAccessible","accessFlags","isEnumConstant",
      "getGenericType","toString","equals","getName"))
  }

  private def methods(): List[MethodToMethodType] = List(
    new MethodToMethodType("get", "(Ljava/lang/Object;)Ljava/lang/Object;", REFLECT_FIELD_GET),
    new MethodToMethodType("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", REFLECT_FIELD_SET),
    new MethodToMethodType("getShort", "(Ljava/lang/Object;)S", REFLECT_FIELD_GET ),
    new MethodToMethodType("setFloat", "(Ljava/lang/Object;F)V", REFLECT_FIELD_SET ),
    new MethodToMethodType("getFloat", "(Ljava/lang/Object;)F", REFLECT_FIELD_GET),
    new MethodToMethodType("getDouble", "(Ljava/lang/Object;)D", REFLECT_FIELD_GET   ),
    new MethodToMethodType("getChar", "(Ljava/lang/Object;)C", REFLECT_FIELD_GET   ),
    new MethodToMethodType("getByte", "(Ljava/lang/Object;)B", REFLECT_FIELD_GET   ),
    new MethodToMethodType("getLong", "(Ljava/lang/Object;)J", REFLECT_FIELD_GET   ),
    new MethodToMethodType("setByte", "(Ljava/lang/Object;B)V", REFLECT_FIELD_SET ),
    new MethodToMethodType("setShort", "(Ljava/lang/Object;S)V", REFLECT_FIELD_SET ),
    new MethodToMethodType("setChar", "(Ljava/lang/Object;C)V",REFLECT_FIELD_SET),
    new MethodToMethodType("setInt", "(Ljava/lang/Object;I)V",REFLECT_FIELD_SET ),
    new MethodToMethodType("setLong", "(Ljava/lang/Object;J)V",REFLECT_FIELD_SET),
    new MethodToMethodType("getBoolean", "(Ljava/lang/Object;)Z",REFLECT_FIELD_GET),
    new MethodToMethodType("setBoolean", "(Ljava/lang/Object;Z)V",REFLECT_FIELD_SET),
    new MethodToMethodType("setDouble", "(Ljava/lang/Object;D)V",REFLECT_FIELD_SET),
    new MethodToMethodType("getInt", "(Ljava/lang/Object;)I",REFLECT_FIELD_GET));

}
