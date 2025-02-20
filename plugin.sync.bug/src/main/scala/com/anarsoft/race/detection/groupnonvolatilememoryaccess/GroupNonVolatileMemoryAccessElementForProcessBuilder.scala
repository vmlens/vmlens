package com.anarsoft.race.detection.groupnonvolatilememoryaccess

import com.anarsoft.race.detection.event.nonvolatile.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer

class GroupNonVolatileMemoryAccessElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[GroupNonVolatileMemoryAccessElementForProcess]();

  def add(list: util.List[NonVolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(new GroupNonVolatileMemoryAccessElementForProcessImpl(EventArray[NonVolatileFieldAccessEvent](list)));
  }


  def build(): List[GroupNonVolatileMemoryAccessElementForProcess] = {
    arrayBuffer.toList
  }

}
