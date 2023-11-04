package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.syncAction.LoadedSyncActionEvent
import com.vmlens.trace.agent.bootstrap.testFixture.StaticEventAndId

import scala.collection.mutable.{ArrayBuffer, Buffer}

class RaceDetectionTestData(val javaEvents: Buffer[StaticEventAndId],
                            val scalaEvent: ArrayBuffer[LoadedSyncActionEvent]) {

}
