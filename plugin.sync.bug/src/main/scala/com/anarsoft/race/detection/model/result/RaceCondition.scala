package com.anarsoft.race.detection.model.result


import com.anarsoft.integration._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import java.io.PrintStream
import com.vmlens.api._
import com.vmlens.api.internal._


class RaceCondition(val reading: RaceConditionElement, val writing: RaceConditionElement, val otherRacesWithSameLocation: Int) extends Equals with IssueModelElement {

  def titlePrefix() = "Data race:";

  def showDetailsTill(modelFacade: ModelFacadeAll) = {
    if (otherRacesWithSameLocation > 1) {
      3;
    }
    else {
      reading.locationInClass match {
        case f: FieldInClass => {

          if (f.isFromTemplate(modelFacade.fieldAndArrayFacade)) {
            1;
          }
          else if (f.getName(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph).startsWith("java.util")) {
            3;
          }
          else {
            0;
          }


        }

        case _: ArrayInClass => 1;

        case _: ClassAccess => 1;


      }


    }
  }


  def getName(fieldAndArrayFacade: FieldAndArrayPerMethodFacade, stackTraceGraph: StackTraceGraph) = fieldAndArrayFacade.getQualifiedFieldName(reading.locationInClass, stackTraceGraph)


  def name(modelFacade: ModelFacadeAll) = getName(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph); //modelFacade.fieldAndArrayFacade.getQualifiedFieldName(reading.locationInClass,modelFacade);

  def icon(modelFacade: ModelFacadeAll) =

    IconRepository.getImageForField(new MemoryAccessType(reading.operation | writing.operation), reading.locationInClass.isStatic, false, true)




  // MemoryOperationType(new  MemoryAccessType( MemoryAccessType.IS_READ_WRITE ) ,  new MemoryAccessAttributes(  reading.locationInClass.isStatic , true )    )

  // def getText(viewTyp : ModelFacade)  = reading.getQualifiedFieldName(viewTyp);

  def children(modelFacade: ModelFacadeAll) = {
    val list = new ArrayBuffer[IssuePartElement]();

    list.append(reading);
    list.append(writing);

    list.toSeq;

  }


  def compare(other: IssueModelElement, modelFacade: ModelFacadeAll) = {

    other match {

      case r: RaceCondition => {
        name(modelFacade).compareTo(r.name(modelFacade))
      }

      case d: Deadlock => {
        -1;
      }

    }

  }


  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.result.RaceCondition]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.model.result.RaceCondition => that.canEqual(RaceCondition.this) && reading == that.reading && writing == that.writing
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + reading.hashCode) + writing.hashCode
  }


  def searchData(modelFacade: ModelFacadeAll) = {
    reading.locationInClass match {

      case FieldInClass(ordinal: Int, isVolatile: Boolean, isStatic: Boolean) => {

        val model = modelFacade.fieldAndArrayFacade.fieldOrdinal2FieldModelDesc(ordinal);

        model.getSearchData();

      }


      case ArrayInClass(methodOrdinalAndPosition, classId: Int) => {
        None;
      }


    }
  }


  def title4Yaml(position: Int) = None;


  def name4Yaml(modelFacade: ModelFacadeAll) = "- variable: " + name(modelFacade);


}

