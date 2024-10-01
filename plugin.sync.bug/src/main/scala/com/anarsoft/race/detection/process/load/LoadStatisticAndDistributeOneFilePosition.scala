package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.loadAndDistribute.DirTypeNameAndLoadAndDistributeOneFilePosition
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithLoopIdAndRunId.STATISTIC_FILE_POSTFIX

import java.io.DataInputStream
import java.nio.file.Files

class LoadStatisticAndDistributeOneFilePosition(val loadStatistic: LoadStatistic,
                                                val loadAndSerializeOneFilePosition: LoadAndDistributeOneFilePosition) {


}

object LoadStatisticAndDistributeOneFilePosition {
  def apply(data: DirTypeNameAndLoadAndDistributeOneFilePosition): LoadStatisticAndDistributeOneFilePosition = {
    val statisticStream = new DataInputStream(Files.newInputStream(data.dir.resolve(data.typeName + STATISTIC_FILE_POSTFIX)));
    val loadStatistic = new LoadStatistic(statisticStream);
    new LoadStatisticAndDistributeOneFilePosition(loadStatistic, data.loadAndDistributeOneFilePosition);
  }
}