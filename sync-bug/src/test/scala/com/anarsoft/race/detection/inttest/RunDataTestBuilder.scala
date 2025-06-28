package com.anarsoft.race.detection.inttest

import com.anarsoft.race.detection.event.interleave.LoadedInterleaveActionContext
import com.anarsoft.race.detection.loopAndRunData.{RunData, RunDataListBuilderImpl}

class RunDataTestBuilder(val loopId  : Int,  val runId  : Int) {

  val loadedInterleaveActionContext = new LoadedInterleaveActionContext();
  private var runPosition = 0;

  def createEventTestBuilder(threadIndex : Int) : EventTestBuilder = new EventTestBuilder(this,threadIndex);

  def build(): RunData = {
    val runDataListBuilderImpl = new RunDataListBuilderImpl();

    runDataListBuilderImpl.build().head;
  }

  def getAndIncrementRunPosition() : Int = {
    val temp = runPosition;
    runPosition = runPosition + 1;
    temp;
  }

}
