package com.anarsoft.race.detection.processEventByType

class EventWithTypeGuineaPig(val category: Int, val position: Int, val keepProcessing: Boolean)
  extends EventWithType[EventWithTypeGuineaPig] {
  override def sameType(other: EventWithTypeGuineaPig): Boolean = category == other.category;
}
