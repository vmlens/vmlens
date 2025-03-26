package com.anarsoft.race.detection.reportbuilder

enum TestResultLabel(val text : String, val style : String) {
  case Success extends TestResultLabel("Success", "")
  case DataRace extends TestResultLabel("Data race", "style=\"color: red;\"")
  case Failure extends TestResultLabel("Failure", "style=\"color: red;\"")
  case FailureAndDataRace extends TestResultLabel("Failure, data race", "style=\"color: red;\"")
}  
