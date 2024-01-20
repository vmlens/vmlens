package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.load.FilePosition

case class SlidingWindowIdToFilePosition(maxSlidingWindowId: Int, map: Map[Int, FilePosition])
