package com.anarsoft.race.detection.model.method

import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.description.StackTraceElementModel;

class StackTraceElement2Id {
  
  var currentId = StackTraceElement2Id.START_STACKTRACE_IDS;
  
  val stackTraceElement2Id = new HashMap[StackTraceElementModel,Int]();
  val id2StackTraceElement = new HashMap[Int,StackTraceElementModel]();
  
  
  
  def getId(element : StackTraceElementModel ) =
  {
    stackTraceElement2Id.get(element) match
    {
      
      case Some(x) => x;
      
      
      case None =>
        {
          stackTraceElement2Id.put ( element, currentId);
          id2StackTraceElement.put( currentId , element);
          val temp = currentId;
          currentId = currentId - 1;
          
          temp;
          
        }
      
      
      
    }
  }
  
  
  def getElement(id : Int) =
  {
    id2StackTraceElement.get(id).get;
  }
  
}

object StackTraceElement2Id
{
  
  val MISSING_LINK_ID = -1;
  val START_STACKTRACE_IDS = -10;

  
}

