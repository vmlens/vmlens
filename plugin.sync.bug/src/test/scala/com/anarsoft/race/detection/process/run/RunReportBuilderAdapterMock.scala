package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.reportbuilder.RunReportBuilderAdapter

import scala.collection.mutable.ArrayBuffer

class RunReportBuilderAdapterMock extends RunReportBuilderAdapter {

  val nonVolatileFieldAccessEventList = new ArrayBuffer[NonVolatileFieldAccessEvent]();
  val dataRaceNonVolatileFieldAccessEventList = new ArrayBuffer[NonVolatileFieldAccessEvent]();

  def add(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent) = {
    nonVolatileFieldAccessEventList.append(nonVolatileFieldAccessEvent);
  }

  def addAsDataRace(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent) = {
    dataRaceNonVolatileFieldAccessEventList.append(nonVolatileFieldAccessEvent);
  }

}
