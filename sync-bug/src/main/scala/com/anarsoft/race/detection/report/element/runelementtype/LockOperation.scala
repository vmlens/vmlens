package com.anarsoft.race.detection.report.element.runelementtype

enum LockOperation(val text: String) {
  case LOCK_ENTER extends LockOperation("Enter")
  case LOCK_EXIT extends LockOperation("Exit")
  case CONDITION_WAIT_ENTER extends LockOperation("Wait Enter")
  case CONDITION_WAIT_EXIT extends LockOperation("Wait Exit")
}