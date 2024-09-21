package com.anarsoft.race.detection.syncactiongroup

import com.anarsoft.race.detection.event.syncAction.VolatileAccessEvent

import java.util
import scala.collection.mutable.ArrayBuffer


class SyncActionElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[SyncActionElementForProcess]();

  def add(list: util.LinkedList[VolatileAccessEvent]): Unit = {

  }

}
