package com.anarsoft.race.detection.process.transformation

import com.anarsoft.race.detection.process.Transformation

import scala.collection.mutable.ListBuffer


class TransformationProcessBuilder {

  val transformations = new ListBuffer[Transformation]();


  def add(transformation: Transformation): Unit = {
    transformations += transformation;
  }

  def build(): TransformationProcess = {
    new TransformationProcess(transformations.toList);
  }


}
