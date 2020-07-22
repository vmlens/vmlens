package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import java.util.Comparator;
import com.anarsoft.race.detection.process.perEventList._
import com.anarsoft.race.detection.process.field._

class PerEventListBuildFieldIdMap4State( val getCurrentList  :   ContextState => ArrayList[_  <: EventField] ) 
extends PerEventListAbstract[EventField,ContextState]   {
  
  
  def getCurrentReadFields ( context : ContextState ) = getCurrentList(context);
  val comparator =   new ComparatorEventField()
  
  
  def processEventList( list :  ArrayList[_  <: EventField]  , context : ContextState ) 
   {
      
              
      
        
      val iter = list.iterator();
     
     while( iter.hasNext() )
     {
       val current = iter.next();
       
       current.fieldOrdinal = context.fieldIdMap.getOrCreate(current.fieldId);
     }
       
   
    
          
          
          
          
   }  
  
  
}

object PerEventListBuildFieldIdMap4State
{
  def apply() =
  {
   new StepProcessSingleEventList[ContextState](new PerEventListBuildFieldIdMap4State(   c => c.stateStaticFieldEventList  ) , classOf[ContextState]);
  }
  
  
  
}