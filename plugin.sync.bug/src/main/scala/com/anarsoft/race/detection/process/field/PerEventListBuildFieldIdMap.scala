package com.anarsoft.race.detection.process.field

import com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import java.util.Comparator;
import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract


class PerEventListBuildFieldIdMap( val getCurrentList  :   ContextFieldIdMap => ArrayList[_  <: EventField] ) 
extends PerEventListAbstract[EventField,ContextFieldIdMap]   {
  
  
  def getCurrentReadFields ( context : ContextFieldIdMap ) = getCurrentList(context);
  val comparator =   new ComparatorEventField()
  
  
  def processEventList( list :  ArrayList[_  <: EventField]  , context : ContextFieldIdMap ) 
   {
      
              
      
        
      val iter = list.iterator();
     
     while( iter.hasNext() )
     {
       val current = iter.next();
       
       
       current.fieldOrdinal = context.fieldIdMap.getOrCreate(current.fieldId);
     }
       
   
    
          
          
          
          
   }  
  
  
}