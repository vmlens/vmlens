package com.anarsoft.race.detection.createautomatictest

import com.anarsoft.race.detection.automatictest.{AutomaticTest, AutomaticTestMethodBuilder, IdToAutomaticTest}
import com.anarsoft.race.detection.createstacktrace.{MethodEvent, MethodEventOrdering}
import com.anarsoft.race.detection.event.automatictest.{AutomaticTestEvent, LoadedAutomaticTestEvent}
import com.anarsoft.race.detection.rundata.RunData
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable

class CreateAutomaticTestFromRunData {

  val idToAutomaticTest = new mutable.HashMap[Int,AutomaticTest]

  /**
   * we have here only evens from type AutomaticTestSuccessEvent
   * @param eventArray
   */
  
  def processDummyLoop(eventArray: EventArray[LoadedAutomaticTestEvent]) : Unit = {
    for(event <- eventArray) {
      idToAutomaticTest.put(event.automaticTestId, new AutomaticTest())
    }
  }

  
  def process(runData: RunData): Unit = {
    
    val threadIndexToBuilder = new mutable.HashMap[Int,AutomaticTestMethodBuilder]
    
    // first we need to check if we have new automatic test events
    for (orig <- runData.automaticTestEvents) {
      val event = orig.asInstanceOf[AutomaticTestEvent]
      
      
      idToAutomaticTest.get(event.automaticTestId) match {

        case None => {
          // this means we did not receive a success event
        }

        case Some(test) => {
          test.createBuilder(event.automaticTestMethodId,event.automaticTestType)  match {
            case None => {
            }
            case Some(builder) => {
              threadIndexToBuilder.put(event.threadIndex,builder);
            }
          }
        }
      }
    }

    // Then for the new events we need to create the call stack tree
    // here we need the thread index in which the methods was tested
    if(! threadIndexToBuilder.isEmpty) {
      val methodEventArray = runData.methodEventArray;
      methodEventArray.sort(new MethodEventOrdering());
      
      for(event <- methodEventArray) {
        threadIndexToBuilder.get(event.threadIndex) match {
          case None=> {
          }
          case Some(builder) => {
            event.addToAutomaticTestBuilder(builder);
          }
        }
      }
      for(elem <- threadIndexToBuilder) {
        elem._2.build();
      }
    }
  }
  
  def build() : IdToAutomaticTest = new IdToAutomaticTest(idToAutomaticTest);
  

}
