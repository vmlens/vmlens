package com.anarsoft.race.detection.process.result


import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.graph._;
import  com.anarsoft.race.detection.model.method._;
import com.anarsoft.race.detection.process.workflow.SingleStep

class StepAddStacktraceIdsAndMissingLink extends SingleStep[ContextAddStacktraceIdsAndMissingLink] {
  
  
  
  def execute(in : ContextAddStacktraceIdsAndMissingLink)
  {
      addStackTraceElementModel( in.methodId2Ordinal  , in.stackTraceGraph.methodOrdinal2Model ,
          in.stackTraceElement2Id.id2StackTraceElement);
      
      
       in.methodId2Ordinal.getOrdinal( StackTraceElement2Id.MISSING_LINK_ID ) match
    {
      case None =>
        {
          // Nicht aufgetreten
        }
      
      
      case Some(ordinal) =>
        {
           in.stackTraceGraph.methodOrdinal2Model (ordinal) = new MissingLink();
        }
      
    }
     
  }
  
  
  def desc = "";
  
  
  
    def addStackTraceElementModel(methodId2Ordinal :  ModelKey2OrdinalMap[Int], methodOrdinal2MethodModel : Array[MethodModel],
          id2StackTraceElement :  HashMap[Int,StackTraceElementModel] )
   {
     for( elem <-  id2StackTraceElement )
     {
        methodId2Ordinal.getOrdinal(elem._1) match
        {
          case None =>
            {
              // Nothing todo
            }
          
          case Some(ordinal) =>
            {
               methodOrdinal2MethodModel(ordinal) = elem._2;
            }
          
          
        }
      
     }
   }
   
  
  
  
}