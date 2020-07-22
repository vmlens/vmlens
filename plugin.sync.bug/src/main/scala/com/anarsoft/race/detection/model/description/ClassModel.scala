package com.anarsoft.race.detection.model.description

import scala.collection.mutable.HashSet



class ClassModel (val source : String, val isThreadSafe : Boolean , val  isStateless : Boolean , val  except : Array[String],val superName : String, val interfaces : Array[String]) {
  
  override def toString() = "source:" + source + ",superName:" + superName + ",interfaces:"+ interfaces.mkString(",");
  
  
}

object ClassModel
{
  def replaceShlashWithDot(in : String) =
   {
     in.replace('/', '.' );
   }
  
  
  def apply() = new ClassModel("",false,false, Array.ofDim[String](0), "" , Array.ofDim[String](0));
  
}