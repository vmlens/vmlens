package com.anarsoft.race.detection.reportbuilder

enum TestResultLabel(val text : String) {
  case Success extends TestResultLabel("Success")
  case DataRace extends TestResultLabel("<span style=\"color: red;\">Data race</span>")
  case Failure extends TestResultLabel("<span style=\"color: red;\">Failure</span>")
  case FailureAndDataRace extends TestResultLabel("<span style=\"color: red;\">Failure, Data Race</span>")
}  
