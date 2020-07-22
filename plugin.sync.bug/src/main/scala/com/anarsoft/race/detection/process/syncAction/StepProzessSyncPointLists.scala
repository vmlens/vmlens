package com.anarsoft.race.detection.process.syncAction


import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.perEventList.PerEventListTrait
import com.anarsoft.race.detection.process.perEventList.PerEventListTrait;


class StepProzessSyncPointLists extends SingleStep[ContextProcessSyncAction] {
  
  val processPerEventListCollection = new ArrayBuffer[PerEventListTrait[ContextProcessSyncAction]]
  
  
  def execute(context : ContextProcessSyncAction)
  {
    
     for( elem <- processPerEventListCollection )
    {
      elem.process(context);
    }
     
   
  }
  
 
  
}