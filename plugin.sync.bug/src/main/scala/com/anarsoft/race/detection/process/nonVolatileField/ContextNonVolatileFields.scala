package com.anarsoft.race.detection.process.nonVolatileField


import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process.gen._
import com.anarsoft.race.detection.process.read._

import java.util.ArrayList


trait ContextNonVolatileFields extends FieldVisitor with ContextBuildAggregate   {
  
  
  var arrayAccessEventList : ArrayList[ArrayAccessEvent] = null;
  var nonVolatileFieldAccessEventList :  ArrayList[NonVolatileFieldAccessEvent] = null;
  var nonVolatileFieldAccessEventStatic : ArrayList[NonVolatileFieldAccessEventStatic] = null;
  
  
  var fieldEventStreams  :  StreamAndStreamStatistic[ApplyFieldEventVisitor]  = null;
  
  

  
  def visit( in :  FieldAccessEventGenInterleave)
  {
    nonVolatileFieldAccessEventList.add(in);
   
  
   
  }
  
  
    def visit( in :  FieldAccessEventGen)
  {
     nonVolatileFieldAccessEventList.add(in);
  }
       

  def visit( in :  FieldAccessEventStaticGen)
  {
      nonVolatileFieldAccessEventStatic.add(in);
  }
  
  
  
  
  def visit( in :  ArrayAccessEventGen)
  {
   
       arrayAccessEventList.add(in);
  }
  
  
   
  
  
  
  
  def addNonVolatileField(event : ApplyFieldEventVisitor)
  {
    event.accept(this);
  }
  
  def resetContextNonVolatileFields()
  {
    arrayAccessEventList = new ArrayList[ArrayAccessEvent]();
    nonVolatileFieldAccessEventList = new   ArrayList[NonVolatileFieldAccessEvent]
    nonVolatileFieldAccessEventStatic = new  ArrayList[NonVolatileFieldAccessEventStatic] 
    
  }
  
}