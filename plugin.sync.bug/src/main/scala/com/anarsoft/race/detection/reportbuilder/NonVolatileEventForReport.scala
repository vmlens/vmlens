package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.util.WithPosition

trait NonVolatileEventForReport extends WithPosition {

  def staticMemoryAccessId(): StaticMemoryAccessId;

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;

  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;

}
