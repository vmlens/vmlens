package com.anarsoft.race.detection.process.setMonitorInfo

import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo



trait EventSetMonitorInfo extends WithStatementPosition {
  
  
  def needsMonitorInfo : Boolean;
  
  
  def setMonitorInfo(  monitorInfo : Option[MonitorInfo] , context : ContextSetMonitorInfo );
  
  
}