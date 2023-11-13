package com.anarsoft.race.detection.process.source

import com.anarsoft.race.detection.process.Source

import scala.collection.mutable.ListBuffer

class SourceProcess(var currentSources: List[Source]) {

  def process(): Unit = {
    val newSources = new ListBuffer[Source]();

    for (source <- currentSources) {
      source.start();
      newSources += source;
    }

    currentSources = newSources.toList;
  }

  def isDone() = currentSources.isEmpty;

}
