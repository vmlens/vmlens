package com.anarsoft.race.detection.report.element.runelementtype

enum ReportLockType(val text: String, val textForMethodWithLock: String) {
  case MONITOR extends ReportLockType("Monitor", "Monitor")
  case REENTRANT_LOCK extends ReportLockType("ReentrantLock", "WriteLock")
  case READ_LOCK extends ReportLockType("ReadLock", "AtomicRead")
  case WRITE_LOCK extends ReportLockType("WriteLock", "WriteLock")
}