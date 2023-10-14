package com.anarsoft.race.detection.process.syncAction


 import collection.JavaConverters._
import com.anarsoft.race.detection.model._;
import com.anarsoft.race.detection.process.workflow.SingleStep
 
 
class StepProzessThreadStoppedEvent extends SingleStep[ContextProcessSyncAction] {

  def execute(context : ContextProcessSyncAction)
  {
    for(   e <-  context.threadStopList.asScala)
    {
      
       
      
      context.threadId2LastProgramCounter.get(e.joinedThreadId) match {

        case None => {


          // hier benötigen wir 2 (durch test nachgewiesen)
          context.partialOrder4SlidingWindowIdBuilder.leftComesBeforeRight(new WithStatementPositionImpl(e.joinedThreadId,
            2), new WithStatementPositionImpl(e.threadId, e.programCounter))
        }
        
        case Some(stoppedProgramCounter) =>
          {
            
              
             // 2 sollte nicht schaden, wegen oben übernommen
            context.partialOrder4SlidingWindowIdBuilder.leftComesBeforeRight(new WithStatementPositionImpl(e.joinedThreadId,
              stoppedProgramCounter + 2), new WithStatementPositionImpl(e.threadId, e.programCounter))
          }
        
      }
      
      
      
       
    
      
    }
  }
  
  
  def desc = "";
  
}