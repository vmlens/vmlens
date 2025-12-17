package com.anarsoft.race.detection.report.element.runelementtype

enum ReportThreadOperation(val text: String) {
  case START extends ReportThreadOperation("Start")
  case JOIN extends ReportThreadOperation("Join")
}