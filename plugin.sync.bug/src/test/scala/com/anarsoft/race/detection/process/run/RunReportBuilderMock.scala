package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.reportbuilder.RunReportBuilder

import scala.collection.mutable.ArrayBuffer

class RunReportBuilderMock extends RunReportBuilder {

  val nonVolatileFieldAccessEventList = new ArrayBuffer[NonVolatileFieldAccessEvent]();
  val dataRaceNonVolatileFieldAccessEventList = new ArrayBuffer[NonVolatileFieldAccessEvent]();

  def add(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent) = {
    nonVolatileFieldAccessEventList.append(nonVolatileFieldAccessEvent);
  }

  def addAsDataRace(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent) = {
    dataRaceNonVolatileFieldAccessEventList.append(nonVolatileFieldAccessEvent);
  }

}
