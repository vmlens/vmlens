package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.model.scheduler.LoopId2Result
import scala.collection.mutable.HashMap

class StepProcessInterleaveEventListAfterRead extends SingleStep[ContextInterleave]  {
  
  def execute(context : ContextInterleave)
  {
     
 context.loopId2Result =  new LoopId2Result( context.interleaveEventList.stopProcessing() , context.loopId2Name );
  
  
  //context.loopId2Result = new LoopId2Result(context.interleaveEventList.id2Result , context.loopId2Name, context.interleaveEventList.id2Warning)
   
//   val loopName2RunCount  = new HashMap[String,Int];
//   
//   for( elem <- context.interleaveEventList.id2MaxRunCount )
//   {
//     loopName2RunCount.put( context.loopId2Name.getOrElse(  elem._1 , "unknown")   ,  elem._2  );
//   }
//   
//   
//   context.loopName2RunCount = loopName2RunCount;
//   
   
  }
}