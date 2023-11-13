package com.anarsoft.race.detection.process.transformation

import com.anarsoft.race.detection.process.Transformation

import scala.collection.mutable.ListBuffer

class TransformationProcess(var currentTransformations: List[Transformation]) {

  def process(): Unit = {
    val newTransformations = new ListBuffer[Transformation]();

    for (transormation <- currentTransformations) {
      if (!transormation.start()) {
        newTransformations += transormation;
      }
    }

    currentTransformations = newTransformations.toList;
  }

  def isDone() = currentTransformations.isEmpty;

}
