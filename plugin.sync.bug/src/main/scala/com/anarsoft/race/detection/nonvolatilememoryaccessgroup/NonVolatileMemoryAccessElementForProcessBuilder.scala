package com.anarsoft.race.detection.nonvolatilememoryaccessgroup

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.setstacktrace.EventWithStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class NonVolatileMemoryAccessElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[NonVolatileMemoryAccessElementForProcess]();

  def add(list: util.List[NonVolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(new NonVolatileMemoryAccessElementForProcessImpl(toEventArray[NonVolatileFieldAccessEvent](list)));
  }

  private def toEventArray[EVENT: ClassTag](list: util.List[EVENT]) = {
    val array = Array.ofDim[EVENT](list.size());
    var index = 0;
    val iter = list.iterator();
    while (iter.hasNext) {
      array(index) = iter.next();
      index = index + 1;
    }
    new EventArray[EVENT](array);
  }

  def build(): List[NonVolatileMemoryAccessElementForProcess] = {
    arrayBuffer.toList
  }

}
