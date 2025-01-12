package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementForResult(val eventArray: EventArray[EventForReportElement]) {

  def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }


}
