package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.integration._
import com.vmlens.api._
import com.vmlens.api.internal._
import java.util.LinkedList


case class Deadlock(val first: DeadlockPart, val second: DeadlockPart) extends Equals with IssueModelElement {


  def titlePrefix() = "Deadlock:";

  def name(modelFacade: ModelFacadeAll) = {
    first.methodName(modelFacade) + "<->" + second.methodName(modelFacade)
  }


  def showDetailsTill(modelFacade: ModelFacadeAll) = 0;


  def icon(modelFacade: ModelFacadeAll) = IconRepository.DEADLOCK;


  def children(modelFacade: ModelFacadeAll) = {
    val list = new ArrayBuffer[IssuePartElement]();

    list.append(first);
    list.append(second);


    list.toSeq;
  }


  def compare(other: IssueModelElement, modelFacade: ModelFacadeAll) = {

    other match {

      case r: RaceCondition => {
        1;
      }

      case d: Deadlock => {
        name(modelFacade).compareTo(d.name(modelFacade))
      }
    }
  }


  def searchData(modelFacade: ModelFacadeAll) = None;


  def title4Yaml(position: Int) = None;


  def name4Yaml(modelFacade: ModelFacadeAll) = "- deadlock: " + name(modelFacade);
}

