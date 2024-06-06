package com.anarsoft.race.detection.processeventbytype

class EventWithTypeGuineaPig(val category: Int, val position: Int, val keepProcessing: Boolean)
  extends EventWithType[EventWithTypeGuineaPig] {

  override def compareType(other: EventWithTypeGuineaPig): Int = category.compare(other.category);
}
