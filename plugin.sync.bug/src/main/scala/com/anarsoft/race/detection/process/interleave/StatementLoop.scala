package com.anarsoft.race.detection.process.interleave

import java.util.ArrayList

case class StatementLoop(val loopCount : Int, val statements: ArrayList[InterleaveEventStatement])  {
  
}