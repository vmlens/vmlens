package com.anarsoft.race.detection.createpartialordersyncaction

import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.processeventbytype.{AlgorithmForOneType, AlgorithmForOneTypeFactory}
import com.anarsoft.race.detection.sortutil.EventContainer

class AlgorithmForOneTypeFactoryCreatePartialOrder[EVENT <: SyncActionEventWithCompareType[EVENT]]
(val partialOrderBuilder: BuildPartialOrderContext, createContainer: (EVENT) => EventContainer[EVENT]) extends AlgorithmForOneTypeFactory[EVENT] {

  override def create(): AlgorithmForOneType[EVENT] =
    new AlgorithmForOneTypeCreatePartialOrder[EVENT](partialOrderBuilder, createContainer);

}
