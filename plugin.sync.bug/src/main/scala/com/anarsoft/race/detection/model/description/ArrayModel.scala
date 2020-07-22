package com.anarsoft.race.detection.model.description

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api.MethodDescriptionWithPosition


class ArrayModel {
  
  val methodAndPosition = new ArrayBuffer[MethodOrdinalAndPosition]
  
  
  def getMethodDescriptionWithPositionList(stackTraceGraph : StackTraceGraph) =
  {
    
    val result = new ArrayBuffer[MethodDescriptionWithPosition]();
    
     for( elem <- methodAndPosition )
    {
       //modelFacade.stackTraceGraph.methodOrdinal2Model(x.methodOrdinal).accept(visitor)
       
       new MethodOrdinal(elem.methodOrdinal).methodDescription(stackTraceGraph) match
       {
         
         case None =>
           {
             
           }
         
         case Some(desc) =>
           {
             result.append(  MethodDescriptionWithPosition(desc, elem.position) );
           }
           
         
       }
       
    }
     
     result;
     
  }
  
  
  def getMinMethodOrdinalAndPosition(stackTraceGraph : StackTraceGraph) =
  {
//    var min : Option[MethodOrdinalAndPosition] = None;
//    
//    for( elem <- methodAndPosition )
//    {
//      min match
//      {
//        case None =>
//          {
//            min = Some(elem);
//          }
//        
//        case Some(x) =>
//          {
//            if( x.methodOrdinal > elem.methodOrdinal )
//            {
//              min = Some(elem);
//            }
//            
//            
//          }
//        
//      }
//       
//        
//    }
//    
//    min;
    
    
    if( methodAndPosition.isEmpty )
    {
      None;
    }
    else
    {
      Some(methodAndPosition.sortBy( x => stackTraceGraph.getMethodModelForOrdinal(x.methodOrdinal).getFullNameWithoutBracket()  ).head);
    }
    
    
  }
  
  
  
  
  
  def getName(stackTraceGraph : StackTraceGraph) =
  {
    
    getMinMethodOrdinalAndPosition(stackTraceGraph) match
    {
      
      case None =>
        {
          ArrayModel.UNKNOWN_FIELD_NAME;
        }
      
      case Some(x) =>
        {
          stackTraceGraph.getMethodModelForOrdinal(x.methodOrdinal).getFullNameWithoutBracket();
        }
    }
    
//    val names = new ArrayBuffer[String];
//    
//    for( elem <- methodAndPosition)
//    {
//      names.append(   stackTraceGraph.methodOrdinal2Model(elem.methodOrdinal).getFullNameWithoutBracket()  );
//    }
//    
//    if( names.isEmpty )
//    {
//       ArrayModel.UNKNOWN_FIELD_NAME;
//    }
//    else
//    {
//      names.sorted.head;
//    }
   
    
    
    
    
  }
  
  
}

object ArrayModel
{
  
   val UNKNOWN_FIELD_NAME = "unknown";
  
  
}


