package com.anarsoft.race.detection.process.perEventList

import java.util.ArrayList;
import java.util.Comparator;

class PerEventList4Aggregate[ID,EVENT,CONTEXT](val getCurrentReadFieldsF : ( CONTEXT ) =>   ArrayList[_ <: EVENT], 
    val comparator : Comparator[EVENT] , val  defaultId :  ID,
    val createCallback : ( CONTEXT ) => PerEventCallback4Aggregate[ID,EVENT]   )  extends  PerEventListAbstract[EVENT,CONTEXT] {
  
  
  
     def getCurrentReadFields ( context : CONTEXT )   =  getCurrentReadFieldsF(context);
  
  
  
     def processEventList(  list : ArrayList[_ <: EVENT] ,context : CONTEXT  )
     {
       val callback = createCallback(context);
       val iter = list.iterator();
       var isFirstRun = true;
       var currentId = defaultId;
     
       while( iter.hasNext() )
       {
         val current = iter.next();
         
         if(  isFirstRun )
         {
           isFirstRun = false;
           currentId = callback.getId(current);
           callback.onEventWithNewId(  current );
           
           
         }
         else if   (  currentId  != callback.getId(current) )
         {
          callback.onEventWithNewId(  current );
          currentId = callback.getId(current);
         }
         else
         {
           callback.onEventWithExistingId(  current );  
         }
         
         
         
         
         
       }
       
       
       callback.afterLoop();
       
    
       
       
       
       
       
     }
  
}