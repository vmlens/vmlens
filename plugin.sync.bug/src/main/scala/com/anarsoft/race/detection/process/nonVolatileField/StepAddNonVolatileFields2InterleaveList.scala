package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.workflow.SingleStep
import java.util.ArrayList;
import com.anarsoft.race.detection.process.interleave.InterleaveEventList

class StepAddNonVolatileFields2InterleaveList  extends SingleStep[ContextAddNonVolatileFields2InterleaveList]  {
  
   def apply2List( list : ArrayList[_], interleaveEventList : InterleaveEventList )
   {
     if( list != null && interleaveEventList != null )
     {
       val iter = list.iterator();
       while(iter.hasNext())
       {
         val current = iter.next();
         
         if( current.isInstanceOf[InterleaveEventNonVolatileAccess] )
         {
           val e = current.asInstanceOf[InterleaveEventNonVolatileAccess]
           if( e.showSharedMemory )
           {
             interleaveEventList.add(e);
           }
           
         }
         
         
       }
       
     }
   }
  
  
   def execute(contextNonVolatileFields: ContextAddNonVolatileFields2InterleaveList) {

    
   apply2List( contextNonVolatileFields.arrayAccessEventList , contextNonVolatileFields.interleaveEventList );
   apply2List( contextNonVolatileFields.nonVolatileFieldAccessEventList , contextNonVolatileFields.interleaveEventList );
   apply2List( contextNonVolatileFields.nonVolatileFieldAccessEventStatic , contextNonVolatileFields.interleaveEventList );
   
     
    
    

    
  }
  
  
}