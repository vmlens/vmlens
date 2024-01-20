package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition

case class StatisticAndSerializeOneFilePosition(statistic: Map[Int, FilePosition],
                                                loadAndSerializeOneFilePosition: LoadAndDistributeOneFilePosition)
