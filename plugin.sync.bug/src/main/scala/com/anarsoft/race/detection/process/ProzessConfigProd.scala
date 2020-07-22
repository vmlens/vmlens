package com.anarsoft.race.detection.process

import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction

class ProzessConfigProd extends ProzessConfig {
  
  
   def checkLocksStep() = None;
   def throwExceptionFromAgent()  = false;
  
}