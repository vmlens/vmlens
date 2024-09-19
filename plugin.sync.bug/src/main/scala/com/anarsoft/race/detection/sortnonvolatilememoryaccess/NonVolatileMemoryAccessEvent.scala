package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.EventWithType
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.util.WithPosition
import org.apache.commons.lang3.BooleanUtils


trait NonVolatileMemoryAccessEvent[EVENT] extends EventWithType[EVENT] with WithPosition {
  def isRead: Boolean;

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;

  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;
  
}
