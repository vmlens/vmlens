package com.vmlens.api

case class MethodDescriptionWithPosition(val   qualifiedClassName : String,val  methodName : String, val methodDesc : String, val position : Int) {
  
  def fullName() = qualifiedClassName + "." + methodName;
  
  
}

object MethodDescriptionWithPosition
{
  def apply(desc : MethodDescription , position : Int) =
  {
    new MethodDescriptionWithPosition( desc.qualifiedClassName , desc.methodName , desc.methodDesc , position   );
  }
  
  
}