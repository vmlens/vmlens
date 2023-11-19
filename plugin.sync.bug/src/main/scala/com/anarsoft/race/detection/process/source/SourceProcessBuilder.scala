package com.anarsoft.race.detection.process.source

import com.anarsoft.race.detection.process.Source

import scala.collection.mutable.ListBuffer

class SourceProcessBuilder {

  val sources = new ListBuffer[Source]();

  def add(source: Source): Unit = {
    sources.append(source);
  }

  def build(): SourceProcess = {
    new SourceProcess(sources.toList);
  }
}
