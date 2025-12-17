package com.anarsoft.race.detection.warning

import com.anarsoft.race.detection.event.control.LoopWarningEvent
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent.*
import com.anarsoft.race.detection.report.element.TestResult


trait Warning {
      def forRun() : Boolean;
      def addToTestResult(descriptionContext : DescriptionContext, testResult : TestResult) : Unit;
      def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit;
    
}

object Warning {

 def apply(loopWarningEvent : LoopWarningEvent) : Warning = {
   if(loopWarningEvent.messageId == TEST_BLOCKED ) {
     new SmallWarningForRun("Blocked");
   } else if(loopWarningEvent.messageId == SYNC_ACTION_LOOP ) {
     new SmallWarning("Synchronized Action Loop");
   } else if(loopWarningEvent.messageId == NON_VOLATILE_LOOP ) {
     new SmallWarning("Non Volatile Action Loop");
   } else if(loopWarningEvent.messageId == NON_YET_IMPLEMENTED ) {
     new NotYetImplemented(loopWarningEvent.messageParam);
   } else if(loopWarningEvent.messageId == MAXIMUM_ITERATIONS_REACHED) {
     new SmallWarning("Maximum Iterations Reached");
   } else if(loopWarningEvent.messageId == MAXIMUM_ALTERNATING_ORDERS_REACHED ) {
     new MaximumAlternatingOrdersReached(loopWarningEvent.messageParam)
   } else {
     throw new RuntimeException("unknown messageId:" + loopWarningEvent.messageId)
   }
 }
  
  
}
