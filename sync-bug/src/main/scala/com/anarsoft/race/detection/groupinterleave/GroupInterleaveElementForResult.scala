package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.report.EventForReportElement

trait GroupInterleaveElementForResult {

  def foreach(f: EventForReportElement => Unit): Unit;

}
