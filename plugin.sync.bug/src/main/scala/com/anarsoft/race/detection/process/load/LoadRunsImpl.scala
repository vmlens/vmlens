package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunData, RunDataListBuilder}
import com.anarsoft.race.detection.process.main.LoadRuns

import scala.collection.mutable


class LoadRunsImpl(val loadStatisticAndSerializeOneFilePositionList
                   : List[LoadStatisticAndDistributeOneFilePosition]) extends LoadRuns {

  override def foreach(f: RunData => Unit): Unit = {
    val loopAndRunIdSet = new mutable.HashSet[LoopAndRunId]()
    var statisticAndSerializeOneFilePositionList = List.empty[StatisticAndSerializeOneFilePosition]
    for (elem <- loadStatisticAndSerializeOneFilePositionList) {
      val result = elem.loadStatistic.loadAndClose();
      statisticAndSerializeOneFilePositionList = statisticAndSerializeOneFilePositionList :+
        StatisticAndSerializeOneFilePosition(result.map, elem.loadAndSerializeOneFilePosition);

      for (entry <- result.map) {
        loopAndRunIdSet.add(entry._1)
      }

    }

    val builder = new RunDataListBuilder();
    for (entry <- loopAndRunIdSet) {
      for (elem <- statisticAndSerializeOneFilePositionList) {
        for (filePosition <- elem.statistic.get(entry)) {
          elem.loadAndSerializeOneFilePosition.load(filePosition, builder);
        }
      }
    }
    val runDataList = builder.build();
    runDataList.foreach(f);
  }
}
