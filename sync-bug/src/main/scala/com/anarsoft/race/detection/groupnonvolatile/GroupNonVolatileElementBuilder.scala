package com.anarsoft.race.detection.groupnonvolatile

import com.anarsoft.race.detection.event.nonvolatile.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer

class GroupNonVolatileElementBuilder {

  val arrayBuffer = new ArrayBuffer[GroupNonVolatileElement]();

  def add(list: util.List[NonVolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(new GroupNonVolatileElementImpl(EventArray[NonVolatileFieldAccessEvent](list)));
  }


  def build(): List[GroupNonVolatileElement] = {
    arrayBuffer.toList
  }

}
