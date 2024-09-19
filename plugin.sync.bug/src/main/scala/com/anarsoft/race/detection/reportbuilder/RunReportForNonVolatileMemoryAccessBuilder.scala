package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent

trait RunReportForNonVolatileMemoryAccessBuilder {

  def add(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent): Unit;

  def addAsDataRace(nonVolatileFieldAccessEvent: NonVolatileFieldAccessEvent): Unit;

}
