package com.anarsoft.race.detection.process.result

import com.anarsoft.trace.agent.runtime.util.AntPatternMatcher
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.description.ThreadNames;
import scala.collection.mutable.HashSet;
import com.anarsoft.integration._;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.filter.FilterMap
import com.anarsoft.race.detection.process.detectRaceConditions.EventWrapperDetectRaceConditions
import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import com.vmlens.api.MethodDescription
import com.anarsoft.config.ConfigValues
import collection.JavaConverters._

class StepCreateModelFacade() extends SingleStep[ContextCreateModelFacade] {


  def execute(contextModelFacade: ContextCreateModelFacade) {

    

    contextModelFacade.modelFacade = new ModelFacadeAll(
      contextModelFacade.stackTraceGraph,
      contextModelFacade.filteredRaces,
      contextModelFacade.threadNames.createThreadFacade(),
      contextModelFacade.fieldAndArrayFacade, contextModelFacade.deadlocks,
      contextModelFacade.loopId2Result, contextModelFacade.agentLog);

  }



}