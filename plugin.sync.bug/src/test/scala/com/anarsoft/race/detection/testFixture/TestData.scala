package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.anarsoft.race.detection.event.method.LoadedMethodEvent
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent


case class TestData(syncActionJavaEvents: List[RuntimeEvent], volatileAccessEvents: List[VolatileAccessEventGen],
                    methodJavaEvents: List[RuntimeEvent], methodEvents: List[LoadedMethodEvent]) {

}
