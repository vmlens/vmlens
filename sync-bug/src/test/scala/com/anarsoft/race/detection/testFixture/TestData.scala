package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileFieldAccessEventGen
import com.anarsoft.race.detection.event.method.LoadedMethodEvent
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent


case class TestData(syncActionJavaEvents: List[RuntimeEvent], volatileAccessEvents: List[VolatileFieldAccessEventGen],
                    methodJavaEvents: List[RuntimeEvent], methodEvents: List[LoadedMethodEvent]) {

}
