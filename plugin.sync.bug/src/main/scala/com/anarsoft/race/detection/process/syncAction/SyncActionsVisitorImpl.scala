package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.model.WithStatementPositionImpl
import com.anarsoft.race.detection.process.gen._


class SyncActionsVisitorImpl(val  context : ContextProcessSyncAction) extends SyncActionsVisitor {
  
  
  var currentReadSlidingWindowId = -1;
  
  /*
   * für voaltile fields wird das in VolatileFieldUtil gemacht
   */
  
  def setMaxProgramCounter(threadId : Long, programCounter : Int)
  {
     context.threadId2LastProgramCounter.get(threadId)  match
           {
             case None =>
          {
              context. threadId2LastProgramCounter.put(threadId ,programCounter )
          }
        
        case Some(x) =>
          {
             context.threadId2LastProgramCounter.put(threadId , Math.max( programCounter , x))
          }
            
               
               
             
           }
  }


  def visit( in :  VolatileAccessEventStaticGen)
  {
    
    
    in.slidingWindowId = currentReadSlidingWindowId;
    
    context.volatileAccessEventStatic.add( in );
  }
       
   def visit( in :  VolatileAccessEventGen)
   {
      
       
      in.slidingWindowId = currentReadSlidingWindowId;
       
       context.volatileAccessEventList.add( in );
   }
   
    def visit( in :  LockEnterEventGen)
  {
  
   
      context.lockEventList.add(in);
   
  }
   
       
  def visit( in :  VolatileArrayAccessEventGen)
  {
      context.volatileAccessArrayEventList.add(in);
   
  }
       
 
  def visit( in :  LockExitEventGen)
  {
        
    
     context.lockEventList.add(in);
    
   
  }
  
  
  def visit( in :  StampedLockEnterEventGen)
  {
     context.lockEventList.add(in);
  }

       
def visit( in :  StampedLockExitEventGen)
{
  
   context.lockEventList.add(in);
}

       

   def visit(in: ThreadStartEventGen) {


     context.partialOrder4SlidingWindowIdBuilder.leftComesBeforeRight(new WithStatementPositionImpl(in.threadId, in.programCounter), new WithStatementPositionImpl(in.startedThreadId, 0))
     setMaxProgramCounter(in.threadId, in.programCounter);
   }


  def visit(in: ThreadJoinedEventGen) {


    context.threadStopList.add(in);
    setMaxProgramCounter(in.threadId, in.programCounter);
  }
  
  
}