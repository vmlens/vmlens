package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.processEventByType.{AlgorithmForEvent, AlgorithmForOneType}
import com.anarsoft.race.detection.sortUtil.EventContainer

class AlgorithmForEventCreatePartialOrder[EVENT <: SyncActionEvent[EVENT]]
(val partialOrderBuilder: PartialOrderBuilder, createContainer: (EVENT) => EventContainer[EVENT]) extends AlgorithmForEvent[EVENT] {

  override def create(event: EVENT): AlgorithmForOneType[EVENT] =
    new AlgorithmForOneTypeCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);

}
