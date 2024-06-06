package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.processeventbytype.{AlgorithmForEvent, AlgorithmForOneType}
import com.anarsoft.race.detection.sortutil.EventContainer

class AlgorithmForEventCreatePartialOrder[EVENT <: SyncActionEvent[EVENT]]
(val partialOrderBuilder: PartialOrderBuilder, createContainer: (EVENT) => EventContainer[EVENT]) extends AlgorithmForEvent[EVENT] {

  override def create(event: EVENT): AlgorithmForOneType[EVENT] =
    new AlgorithmForOneTypeCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);

}
