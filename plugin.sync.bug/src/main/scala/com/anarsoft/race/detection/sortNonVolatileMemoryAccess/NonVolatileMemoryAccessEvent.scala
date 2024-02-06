package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.processEventByType.EventWithType
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.eventView.MemoryAccessView

trait NonVolatileMemoryAccessEvent[EVENT] extends EventWithType[EVENT] with WithPosition with MemoryAccessView {
    def isRead: Boolean;
}
