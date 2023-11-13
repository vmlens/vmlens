package com.anarsoft.race.detection.process.execute

import com.anarsoft.race.detection.process.source.SourceProcess
import com.anarsoft.race.detection.process.transformation.TransformationProcess

class ExecuteSourceAndTransformationProcess(val sourceProcess: SourceProcess,
                                            val transformationProcess: TransformationProcess) {
  def execute(): Unit = {
    while (!sourceProcess.isDone() || !transformationProcess.isDone()) {
      sourceProcess.process();
      transformationProcess.process();
    }
  }

}
