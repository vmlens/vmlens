package com.anarsoft.race.detection.testFixture

import com.vmlens.trace.agent.bootstrap.testFixture.VolatileFixture

import scala.jdk.CollectionConverters.*

class RaceDetectionVolatileFixture {

 def volatileReadAndWrite(): RaceDetectionTestData = {
  val eventBuilder = new EventBuilderForScalaEvents();
  val javaEvents = new VolatileFixture(eventBuilder).volatileReadAndWrite().resultTestBuilder().givenEvents().asScala;
  new RaceDetectionTestData(javaEvents, eventBuilder.syncActionEvents);
 }

}
