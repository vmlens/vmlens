package com.anarsoft.race.detection.groupnonvolatilememoryaccess

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.setstacktrace.EventWithStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer

class NonVolatileMemoryAccessElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[NonVolatileMemoryAccessElementForProcess]();

  def add(list: util.List[NonVolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(new NonVolatileMemoryAccessElementForProcessImpl(EventArray[NonVolatileFieldAccessEvent](list)));
  }


  def build(): List[NonVolatileMemoryAccessElementForProcess] = {
    arrayBuffer.toList
  }

}
