package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;


case class TestData(javaEvents: Seq[StaticEvent], volatileAccessEvents: Seq[VolatileAccessEventGen]) {

}
