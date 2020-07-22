package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.model.result._;
import com.vmlens.trace.agent.bootstrap.util.Constants._
import  com.vmlens.api.internal.reports.Model2View
trait InterleaveNamesAsStamped {
      def stackTraceOrdinal : Int;
      def stampedLockMethodId  : Int;
  
     

  
    def methodModel(modelFacade : ModelFacadeAll) = 
      if( stampedLockMethodId == STAMPED_PRIVATE  )
      {
          modelFacade.stackTraceGraph.methodModel4Lock(stackTraceOrdinal);
      }
      else
      {
         modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal));
      }
      
      
      
      
      /*
       * 	public static final int STAMPED_READ_LOCK = 0;
	public static final int STAMPED_WRITE_LOCK = 1;
	
	public static final int STAMPED_READ_UNLOCK = 2;
	public static final int STAMPED_WRITE_UNLOCK = 3;
	
	public static final int STAMPED_UNLOCK = 4;
	
	
	public static final int STAMPED_PRIVATE = 5;
	
	
       */
    
   
    def getLockText( in : String ) =
    {
        Model2View.makeBreakable(  "java.util.concurrent.locks.StampedLock." )+  "<strong>" + in +"</strong>"
    }
    
    

   def operationText(modelFacade : ModelFacadeAll)  = {
     stampedLockMethodId match
     {
       case STAMPED_READ_LOCK =>
         {
         getLockText("readLock");
         }
     
       case STAMPED_WRITE_LOCK =>
         {
            getLockText("writeLock");
           
     
         }
         
      case STAMPED_READ_UNLOCK =>
         {
           getLockText("unlockRead");
           
           
          
         }
         
       case STAMPED_WRITE_UNLOCK =>
         {
             getLockText("unlockWrite");
           
        
         }
         
         
       case STAMPED_UNLOCK =>
         {
              getLockText("unlock");
           
           
         }  
         
         
      case STAMPED_PRIVATE =>
         {
               modelFacade.stackTraceGraph.operationText4Lock(stackTraceOrdinal);
         }  
           
       
       
     }
     
     
     
     
 
   }
     
 
}