package com.anarsoft.race.detection.report

import com.anarsoft.race.detection.report.element.RunElement
import com.anarsoft.race.detection.warning.Warning
import com.vmlens.report.overview.NeedsRunLink

class ReportLoopData(val loopId: Int,
                     val isFailure: Boolean,
                     val dataRaceCount: Int,
                     val warningIdList: Set[Warning],
                     val count: Int,
                     val runElements : List[RunElement]) extends NeedsRunLink {
  
  var runLink : String = null;

  override def setRunLink(link: String): Unit = {
    runLink = link;
  }
  
}
