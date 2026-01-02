package com.anarsoft.race.detection.report.element.runelementtype

enum BarrierOperationType(val text : String) {
  case NOTIFY     extends BarrierOperationType("Notify")
  case WAIT_ENTER extends BarrierOperationType("Wait Enter")
  case WAIT_EXIT  extends BarrierOperationType("Wait Exit")
}