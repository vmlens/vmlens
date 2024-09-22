package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.EventWithType
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.sortutil.MemoryAccessEvent
import com.anarsoft.race.detection.util.WithPosition
import org.apache.commons.lang3.BooleanUtils


trait NonVolatileMemoryAccessEvent[EVENT] extends MemoryAccessEvent[EVENT] with EventWithType[EVENT] with WithPosition {

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;
  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit;
  
}
