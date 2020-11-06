package com.anarsoft.race.detection.process.interleave.loopAlgo

sealed abstract  class Action {
  
}

case class Take() extends Action;


case class Filter() extends Action;