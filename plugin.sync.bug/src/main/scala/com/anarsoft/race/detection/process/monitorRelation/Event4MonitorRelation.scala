package com.anarsoft.race.detection.process.monitorRelation

import com.anarsoft.race.detection.model.WithStatementPosition;

trait Event4MonitorRelation  extends WithStatementPosition {
  
  
  def onMonitorEnter( f :  Event4MonitorRelationEnter => Unit  );
  def onMonitorExit( f :  Event4MonitorRelationExit => Unit  );
  
}