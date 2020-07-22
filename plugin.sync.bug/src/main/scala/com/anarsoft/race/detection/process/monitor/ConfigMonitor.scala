package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.syncAction.PerEventCallbackSyncPoint
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal4Monitor
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection


object ConfigMonitor {
  
  def getMonitorEventList( context : ContextMonitor ) = context.monitorEventList;
  
  
   def initializePerEventListSteps(perEventListSteps : PerEventListStepCollection)
  {
         perEventListSteps.prozessSyncPointLists.processPerEventListCollection.append(PerEventCallbackSyncPoint( getMonitorEventList ,  -1 ))

         perEventListSteps.setStacktraceOrdinal.processPerEventListCollection.append( new PerEventListSetStacktraceOrdinal4Monitor() )
     
  }
  
  
}