package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.processeventbytype.WithCompareType
import com.anarsoft.race.detection.report.NonVolatileEventForReport
import com.anarsoft.race.detection.sortutil.EventWithReadWrite



trait NonVolatileMemoryAccessEvent[EVENT] extends EventWithReadWrite[EVENT]
  with WithCompareType[EVENT] with NonVolatileEventForReport {


}
