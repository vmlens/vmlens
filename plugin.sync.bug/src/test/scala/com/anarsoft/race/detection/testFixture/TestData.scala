package com.anarsoft.race.detection.testFixture

import com.anarsoft.race.detection.event.gen.VolatileAccessEventGen
import com.anarsoft.race.detection.event.method.LoadedMethodEvent
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent


case class TestData(syncActionJavaEvents: List[SerializableEvent], volatileAccessEvents: List[VolatileAccessEventGen],
                    methodJavaEvents: List[RuntimeEvent], methodEvents: List[LoadedMethodEvent]) {

}
