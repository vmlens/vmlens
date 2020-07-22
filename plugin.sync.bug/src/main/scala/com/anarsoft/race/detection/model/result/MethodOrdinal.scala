package com.anarsoft.race.detection.model.result

import com.anarsoft.race.detection.model.description._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import com.anarsoft.integration._
import com.vmlens.api._
import com.vmlens.api.internal._
import java.util.LinkedList
import com.anarsoft.race.detection.model.description.StackTraceElementModel
import com.vmlens.api.MethodDescription


class MethodOrdinal(val ordinal : Int) extends Equals   {

  def fullName(viewTyp : ModelFacade) = 
    {
       val model = viewTyp.stackTraceGraph.getMethodModelForOrdinal(ordinal); 
       
       if(model == null)
       {
          println(" model == null  " + ordinal);
           "unknown";
       }
       else
       {
          model.getFullName()
       }
       
     
    }
//  
  
  
  

  def name(stackTraceGraph : StackTraceGraph) =    stackTraceGraph.getMethodModelForOrdinal(ordinal).getFullName();
   
  
  
   
 
  
  
  def getMethodModel(stackTraceGraph : StackTraceGraph) = stackTraceGraph.getMethodModelForOrdinal(ordinal);
  
  
  
  
  
  def methodDescription(stackTraceGraph : StackTraceGraph) =
  {
   
    
    val model = stackTraceGraph.getMethodModelForOrdinal(ordinal);
    
    if( model == null )
    {
       println(" model == null  " + ordinal);
      
      Some(new MethodDescription("unknown" , "unknown"  ,"()V" ))
    }
    else{
    
    
    model.accept(  new MethodModelVisitor[Option[MethodDescription]] 
  {
        def visit(element: MethodModelFromTrace) =
        {
          // 	public MethodDescription(String packageName, String className, String methodName, String methodDesc) {
          Some(new MethodDescription( element.className , element.methodName , element.desc ));
        }
        
        def visit(element: StackTraceElementModel) =
        {
          None;
        }
        
       def visit(element: MissingLink) =
       {
         None;
       }
      
      
      
  } )
    }
    
  }
 
  
  
  
  
 
  
 

  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.result.MethodOrdinal]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.model.result.MethodOrdinal => that.canEqual(MethodOrdinal.this) && ordinal == that.ordinal
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime + ordinal.hashCode
  }
  
}
 
