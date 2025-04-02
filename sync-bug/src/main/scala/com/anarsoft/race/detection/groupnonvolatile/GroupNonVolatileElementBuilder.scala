package com.anarsoft.race.detection.groupnonvolatile

import com.anarsoft.race.detection.event.nonvolatile.{ArrayAccessEvent, NonVolatileFieldAccessEvent, NonVolatileFieldAccessEventStatic}
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer

class GroupNonVolatileElementBuilder {

  val arrayBuffer = new ArrayBuffer[GroupNonVolatileElement]();

  def addFieldAccess(list: util.List[NonVolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(new GroupNonVolatileElementImpl(EventArray[NonVolatileFieldAccessEvent](list)));
  }

  def addStaticFieldAccess(list: util.List[NonVolatileFieldAccessEventStatic]): Unit = {
    arrayBuffer.append(new GroupNonVolatileElementImpl(EventArray[NonVolatileFieldAccessEventStatic](list)));
  }

  def addArrayAccess(list: util.List[ArrayAccessEvent]): Unit = {
    arrayBuffer.append(new GroupNonVolatileElementImpl(EventArray[ArrayAccessEvent](list)));
  }


  def build(): List[GroupNonVolatileElement] = {
    arrayBuffer.toList
  }

}
