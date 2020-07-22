package com.anarsoft.race.detection.process.method

import com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import java.util.ArrayList;

class MethodFlowBlock(val start : Int) {
  
  val list =  new ArrayList[StackTraceOrdinalAndMethodId]();
  
  
  def lastElementId() =  start + list.size();
  
  
  def add( stackTraceOrdinalAndMethodId : StackTraceOrdinalAndMethodId)
  {
   
    
   
       list.add(stackTraceOrdinalAndMethodId);
    
  
    
  }
  
  
  def get( index : Int ) =
  {
      val realIndex = index - start;
    
  
     
     list.get(realIndex);
   
    
    
  }
  
  
  
}