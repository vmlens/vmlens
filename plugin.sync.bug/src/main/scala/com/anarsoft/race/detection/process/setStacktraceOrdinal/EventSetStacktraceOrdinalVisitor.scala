package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.volatileField.VolatileAccessEvent
import com.anarsoft.race.detection.process.volatileField.VolatileAccessEventStatic
import com.anarsoft.race.detection.process.volatileField.VolatileArrayAccessEvent
import com.anarsoft.race.detection.process.nonVolatileField._;
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.syncAction.SyncActionLock

trait EventSetStacktraceOrdinalVisitor {
  
  def visit( event : VolatileAccessEvent  );
  def visit( event : VolatileAccessEventStatic  );
  def visit( event : ArrayAccessEvent  );
  def visit( event : NonVolatileFieldAccessEvent  );
  def visit( event : NonVolatileFieldAccessEventStatic  );
  def visit( event : MonitorEvent  );
  def visit( event : SyncActionLock  );
  def visit( event : VolatileArrayAccessEvent  );
  
}