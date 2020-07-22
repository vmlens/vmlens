package com.anarsoft.race.detection.process.mode.state

import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.result._;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process._
import com.anarsoft.race.detection.process.facade.ContextFacade
import com.anarsoft.race.detection.process.sharedState.ContextSharedState
import scala.collection.mutable.HashMap
import java.util.ArrayList;
import com.anarsoft.race.detection.process.volatileField._;
import com.anarsoft.race.detection.process.state._


class MainContextSharedState  extends
      ContextCreateStackTraceGraph
     with ContextReadDescription
     with ContextStackTraceGraphBuilder   
     with NoOpContext
     with ContextFieldAndArrayFacade4State
     with ContextFacade
     with ContextSharedState
     with ContextCreateModelFacadeState
     with ContextProcessTemplate
     with ContextReadMethodAndStateEvent
     with ContextFieldIdMap4State
     with ContextClassName
     with ContextInitialState
{






  
}