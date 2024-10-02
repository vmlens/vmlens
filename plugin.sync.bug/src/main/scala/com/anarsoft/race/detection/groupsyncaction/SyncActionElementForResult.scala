package com.anarsoft.race.detection.groupsyncaction

import com.anarsoft.race.detection.reportbuilder.EventForRunElement
import com.anarsoft.race.detection.util.EventArray

class SyncActionElementForResult(val eventArray: EventArray[EventForRunElement]) {

  def foreach(f: EventForRunElement => Unit): Unit = {
    eventArray.foreach(f);
  }


}
