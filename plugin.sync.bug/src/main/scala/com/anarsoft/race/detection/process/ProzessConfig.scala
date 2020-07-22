package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction
import com.anarsoft.race.detection.process.workflow.Step


trait ProzessConfig {
  
   def checkLocksStep() : Option[Step[ContextProcessSyncAction]];
  
   def throwExceptionFromAgent() : Boolean;
   
}