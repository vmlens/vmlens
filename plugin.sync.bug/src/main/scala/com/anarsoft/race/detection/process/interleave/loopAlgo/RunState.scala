package com.anarsoft.race.detection.process.interleave.loopAlgo

sealed abstract class RunState {
  
}


case class Open() extends RunState;

case class Closed() extends RunState;

case class Taken() extends RunState;

case class Filtered() extends RunState;
