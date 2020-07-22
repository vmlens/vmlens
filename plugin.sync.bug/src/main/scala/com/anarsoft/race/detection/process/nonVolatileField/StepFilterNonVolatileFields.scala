package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.detectRaceConditions.EventDetectRaceConditions
import java.util.ArrayList;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.process.SortArrayList;

class StepFilterNonVolatileFields extends SingleStep[ContextNonVolatileFields] {

  val UNDEFINED_THREAD_ID = -1L;
  val MULTIPLE_THREAD_IDS = -10L;

  def filter[ID_PER_OBJECT, EVENT <: EventDetectRaceConditions[ID_PER_OBJECT]](list: ArrayList[EVENT], defaultUniqueId: ID_PER_OBJECT) =
    {
      if (list == null) {

        list;

      } else {

        val filterIds = new HashSet[ID_PER_OBJECT]
        var currentThreadId = UNDEFINED_THREAD_ID;

        SortArrayList.sort(list, new Comparator4Filter[ID_PER_OBJECT, EVENT]);

        val iter = list.iterator();
        var firstElement = true;
        var currentUniqueId = defaultUniqueId;

        while (iter.hasNext()) {

          val current = iter.next();
      if (firstElement) {
            currentUniqueId = current.idPerMemoryLocation;
            currentThreadId = current.threadId;

          } else {

            if (currentUniqueId != current.idPerMemoryLocation) {
              if (currentThreadId != MULTIPLE_THREAD_IDS) {
                filterIds.add(currentUniqueId);
              }

              currentUniqueId = current.idPerMemoryLocation;
              currentThreadId = current.threadId;

            } else {

              if (currentThreadId != current.threadId) {
                currentThreadId = MULTIPLE_THREAD_IDS;
              }

            }
          }
      
      firstElement = false;
         
      
        }
        
        
        if(currentThreadId != MULTIPLE_THREAD_IDS && currentUniqueId != defaultUniqueId)
        {
           filterIds.add(currentUniqueId);
        }
        
      
        
       if( filterIds.isEmpty)
       {
         list
       }
       else
       {
          val iter = list.iterator();
          val newList = new ArrayList[EVENT]
          
          while( iter.hasNext() )
          {
            val current = iter.next();
            
           
            
            
            if(  ! filterIds.contains(  current.idPerMemoryLocation ) )
            {
              newList.add( current );
            }
            
            
            
            
          }
          
         
         newList;
       }
        
        
        
        
        

      }

    }

  def execute(contextNonVolatileFields: ContextNonVolatileFields) {

    contextNonVolatileFields.nonVolatileFieldAccessEventList = filter(    contextNonVolatileFields.nonVolatileFieldAccessEventList ,    new NonVolatileFieldId(-1L, -1)    );
    contextNonVolatileFields.nonVolatileFieldAccessEventStatic = filter(    contextNonVolatileFields.nonVolatileFieldAccessEventStatic ,   -1   );
    
  }

}