package com.anarsoft.race.detection.processeventbytype


private case class AlgorithmForOneTypeAndEvent[EVENT <: EventWithType[EVENT]]
(algorithm: AlgorithmForOneType[EVENT], event: EVENT) {
}
