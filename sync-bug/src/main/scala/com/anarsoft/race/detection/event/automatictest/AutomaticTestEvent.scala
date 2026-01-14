package com.anarsoft.race.detection.event.automatictest

trait AutomaticTestEvent extends LoadedAutomaticTestEvent {

  def automaticTestMethodId : Int;
  def automaticTestType  : Int;
  def threadIndex  : Int

}
