package com.anarsoft.race.detection.processeventbytype


private case class AlgorithmForOneTypeAndEvent[EVENT <: WithCompareType[EVENT]]
(algorithm: AlgorithmForOneType[EVENT], event: EVENT) {
}
