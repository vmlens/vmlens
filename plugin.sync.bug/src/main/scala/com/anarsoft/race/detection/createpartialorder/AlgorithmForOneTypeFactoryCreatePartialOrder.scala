package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.processeventbytype.{AlgorithmForOneType, AlgorithmForOneTypeFactory}
import com.anarsoft.race.detection.sortutil.EventContainer

class AlgorithmForOneTypeFactoryCreatePartialOrder[EVENT <: SyncActionEvent[EVENT]]
(val partialOrderBuilder: PartialOrderBuilder, createContainer: (EVENT) => EventContainer[EVENT]) extends AlgorithmForOneTypeFactory[EVENT] {

  override def create(): AlgorithmForOneType[EVENT] =
    new AlgorithmForOneTypeCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);

}
