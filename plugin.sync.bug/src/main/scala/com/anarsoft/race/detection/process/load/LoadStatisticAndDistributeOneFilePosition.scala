package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.loadanddistribute.DirTypeNameAndLoadAndDistributeOneFilePosition
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.STATISTIC_FILE_POSTFIX

import java.io.DataInputStream
import java.nio.file.Files

class LoadStatisticAndDistributeOneFilePosition(val loadStatistic: LoadStatistic,
                                                val loadAndSerializeOneFilePosition: LoadAndDistributeOneFilePosition) {


}

object LoadStatisticAndDistributeOneFilePosition {
  def apply(data: DirTypeNameAndLoadAndDistributeOneFilePosition): LoadStatisticAndDistributeOneFilePosition = {
    val path = data.dir.resolve(data.typeName + STATISTIC_FILE_POSTFIX);

    if (!Files.exists(path)) {
      new LoadStatisticAndDistributeOneFilePosition(new LoadStatisticNoOp(), data.loadAndDistributeOneFilePosition)
    } else {
      val statisticStream = new DataInputStream(Files.newInputStream(path));
      val loadStatistic = new LoadStatisticImpl(statisticStream);
      new LoadStatisticAndDistributeOneFilePosition(loadStatistic, data.loadAndDistributeOneFilePosition)

    }
  }
}