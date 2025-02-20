package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.WithCompareType
import com.anarsoft.race.detection.reportbuilder.NonVolatileEventForReport
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.util.WithPosition
import org.apache.commons.lang3.BooleanUtils


trait NonVolatileMemoryAccessEvent[EVENT] extends EventWithReadWrite[EVENT]
  with WithCompareType[EVENT] with NonVolatileEventForReport {


}
