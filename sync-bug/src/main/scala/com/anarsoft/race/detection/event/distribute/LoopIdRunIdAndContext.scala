package com.anarsoft.race.detection.event.distribute

case class LoopIdRunIdAndContext[EVENT](loopId : Int, runId : Int, context : LoadedEventContext[EVENT]) {

}
