package com.vmlens.codeGenerator.domain

abstract sealed class FieldOccurence {
  
  def isInJavaEvent() : Boolean;
  def isInScalaEvent()  : Boolean
  def onlyFirstWrite() : Boolean;
  
}


case class All() extends FieldOccurence
{

   def isInJavaEvent() = true;
   def isInScalaEvent()     = true;
   def onlyFirstWrite()  = false;
  
}




case class OnlyFirstWrite() extends FieldOccurence
{

   def isInJavaEvent() = true;
   def isInScalaEvent()     = true;
   def onlyFirstWrite()  = true;
 
  
  
}


case class ScalaEventOnly() extends FieldOccurence
{

   def isInJavaEvent() = false;
   def isInScalaEvent()     = true;
   def onlyFirstWrite()  = false;
 
  
}






