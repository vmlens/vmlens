package com.anarsoft.race.detection.process.perEventList

import java.util.ArrayList;
import java.util.Comparator;
import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric

class PerEventList4Collecting[ID, EVENT <: SyncPointGeneric[ID],CONTEXT](
    val getCurrentReadFieldsF : ( CONTEXT ) =>   ArrayList[_ <: EVENT], 
    val comparator : Comparator[EVENT] , 
    val createCallback : ( CONTEXT ) => PerEventCallback4Collecting[ID,EVENT] , val defaultId : ID   )  extends PerEventListTrait[CONTEXT] {
  
  
  
  def getCurrentReadFields ( context : CONTEXT )   =  getCurrentReadFieldsF(context);
  var callbackOption : Option[PerEventCallback4Collecting[ID,EVENT]] = None;
 
  
//  def filterList(list :  ArrayList[_ <: EVENT]) =
//  {
//      val filteredList = new ArrayList[EVENT]
//    
//       SortArrayList.sort(    list ,comparator);
//       var currentStartsHappensBefore : Option[EVENT] = None;
//       var currentEndsHappensBefore : Option[EVENT] = None;
//     
//       var currentThreadId = -1L;
//       var currentId = defaultId
//       
//        val iter = list.iterator();
//       
//       while( iter.hasNext() )
//       {
//          val current = iter.next();
//          
//          if( current.threadId != currentThreadId || currentId != current.idPerMemoryLocation )
//          {
//            currentStartsHappensBefore.foreach( x =>   filteredList.add(x) );
//            currentEndsHappensBefore.foreach( x =>   filteredList.add(x) );
//            
//            
//            
//            currentStartsHappensBefore = None;
//            currentEndsHappensBefore = None;
//           
//          }
//        
//          
//          currentThreadId =  current.threadId ;
//          currentId = current.idPerMemoryLocation
//          
//         
//          if( current.eventStartsHappensBeforeRelation() )
//          {
//            currentStartsHappensBefore = Some(current);
//          }
//         
//           if( current.eventEndsHappensBeforeRelation() )
//          {
//            currentEndsHappensBefore = Some(current);
//          }
//      }
//       
//       
//       
//        currentStartsHappensBefore.foreach( x =>   filteredList.add(x) );
//        currentEndsHappensBefore.foreach( x =>   filteredList.add(x) );
//   
//       filteredList
//  }
  
  
  
  def process( context : CONTEXT  )
  {
    
 
    
    val list = getCurrentReadFields(context);
    
 
    
    
    if( list != null )
    {
      val callback = callbackOption match
      {
        case None =>
          {
            val x = createCallback(context);
            callbackOption = Some(x);
            
            x;
          }
          
        case Some(x) => x;   
        
        
      }
      
    // filter scheint nicht soviel zu bringen  
    //  val filtered = filterList(list);
      
      val currentFields = callback.addPrevoiusEvents(list.asInstanceOf[ArrayList[EVENT]]);
      callback.resetCallback();
      var isFirstRun = true;
      var currentId = defaultId
      
      SortArrayList.sort(    currentFields ,comparator);
      
      
     val iter = currentFields.iterator();
     var position = 0;
     
       while( iter.hasNext() )
       {
         val current = iter.next();
         
         if(  isFirstRun )
         {

           isFirstRun = false;
           currentId = callback.getId(current);
     
          
         }
         else if(    currentId != callback.getId(current)  )
         {
            callback.processPrevoiusEvents(currentId);
            currentId = callback.getId(current);
         
         }
         
         
         callback.onEvent(current,position);
         
         position = position + 1;
         
         
         
       }
       
       
       if( ! isFirstRun  )
       {
          callback.processPrevoiusEvents(currentId);
       }
       
      
      
      
    }
    
    
    
    
    
  }
  
  
  
   
   
  
}