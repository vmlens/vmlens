package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.loopAndRunData.{RunData, RunDataListBuilder}
import com.anarsoft.race.detection.process.main.LoadRuns


class LoadRunsImpl(val loadStatisticAndSerializeOneFilePositionList
                   : List[LoadStatisticAndDistributeOneFilePosition]) extends LoadRuns {

  override def foreach(f: RunData => Unit): Unit = {
    var maxSlidingWindowId = -1;
    var statisticAndSerializeOneFilePositionList = List.empty[StatisticAndSerializeOneFilePosition]
    for (elem <- loadStatisticAndSerializeOneFilePositionList) {
      val result = elem.loadStatistic.loadAndClose();
      maxSlidingWindowId = Math.max(result.maxSlidingWindowId, maxSlidingWindowId);
      statisticAndSerializeOneFilePositionList = statisticAndSerializeOneFilePositionList :+
        StatisticAndSerializeOneFilePosition(result.map, elem.loadAndSerializeOneFilePosition);
    }

    val builder = new RunDataListBuilder();
    for (slidingWindowId <- 0 to maxSlidingWindowId) {
      for (elem <- statisticAndSerializeOneFilePositionList) {
        for (filePosition <- elem.statistic.get(slidingWindowId)) {
          elem.loadAndSerializeOneFilePosition.load(filePosition, builder);
        }
      }
    }
    val runDataList = builder.build();
    runDataList.foreach(f);
  }
}
