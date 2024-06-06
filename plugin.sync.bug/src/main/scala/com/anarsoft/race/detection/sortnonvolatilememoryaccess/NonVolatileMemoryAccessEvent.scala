package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.EventWithType
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.dataView.MemoryAccessView

trait NonVolatileMemoryAccessEvent[EVENT] extends EventWithType[EVENT] with WithPosition with MemoryAccessView {
  def isRead: Boolean;
}
