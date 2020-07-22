package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal


object ConfigLocks {
  
   def getLockEventList( context : ContextProcessSyncAction ) = context.lockEventList;
  
  
   def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
         perEventListSteps.prozessSyncPointLists.processPerEventListCollection.append(
             PerEventCallbackSyncPoint( getLockEventList ,  -1 ))

        perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal(getLockEventList) )
     
  }
  
}