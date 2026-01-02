package com.anarsoft.race.detection.process.loadDescription

import com.anarsoft.race.detection.event.nonvolatile.LoadedNonVolatileEvent
import com.anarsoft.race.detection.process.load.{DataRaceFilter, EventFilter}
import com.anarsoft.race.detection.process.main.LoadDescription
import com.anarsoft.race.detection.report.description.DescriptionCallback
import com.vmlens.trace.agent.bootstrap.description.{DeserializeClassDescriptions, DeserializeThreadAndLoopDescription, LoopControl}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.{DESCRIPTION, LOOP_CONTROL, THREAD_AND_LOOP_DESCRIPTION}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX

import java.io.{DataInputStream, EOFException}
import java.nio.file.{Files, Path}
import scala.collection.mutable

class LoadDescriptionImpl(dir: Path) extends LoadDescription {

  override def load(descriptionBuilder: DescriptionCallback): Unit = {
    loadClassDescription(descriptionBuilder);
    loadThreadAndLoopDescription(descriptionBuilder);
  }

  def hasThreadAndLoopDescription: Boolean =
    dir.resolve(THREAD_AND_LOOP_DESCRIPTION + EVENT_FILE_POSTFIX).toFile.exists()


  def loadDataRaceFilter(): EventFilter[LoadedNonVolatileEvent] = new DataRaceFilter(loadDataRaceMap());

  private def loadDataRaceMap(): Map[Int, Set[Int]] = {
    if (!dir.resolve(LOOP_CONTROL + EVENT_FILE_POSTFIX).toFile.exists()) {
      Map();
    }
    else {
      val map = new mutable.HashMap[Int, Set[Int]]
      val stream = new DataInputStream(Files.newInputStream(dir.resolve(LOOP_CONTROL + EVENT_FILE_POSTFIX)))
      try {
        while (true) {
          val loopControl = LoopControl.deserialize(stream);
          val set = new mutable.HashSet[Int]();
          for (i <- loopControl.intentionalDataRaces) {
            set.add(i)
          }
          map.put(loopControl.loopId(), set.toSet)
        }
      } catch {
        case _: EOFException => // ignore
      }
      map.toMap;
    }
  }

  private def loadClassDescription(descriptionBuilder: DescriptionCallback): Unit = {
    val stream = new DataInputStream(Files.newInputStream(dir.resolve(DESCRIPTION + EVENT_FILE_POSTFIX)))
    val deserializeClassDescriptions = new DeserializeClassDescriptions();
    val classDescriptionIter = deserializeClassDescriptions.deserialize(stream).iterator();

    while (classDescriptionIter.hasNext) {
      descriptionBuilder.addClassDescription(classDescriptionIter.next());
    }
    stream.close();
  }

  private def loadThreadAndLoopDescription(descriptionBuilder: DescriptionCallback): Unit = {
    val stream = new DataInputStream(Files.newInputStream(dir.resolve(THREAD_AND_LOOP_DESCRIPTION + EVENT_FILE_POSTFIX)))
    val deserializeThreadAndLoopDescription = new DeserializeThreadAndLoopDescription();
    val threadAndLoopDescriptionIter = deserializeThreadAndLoopDescription.deserialize(stream).iterator();

    while (threadAndLoopDescriptionIter.hasNext) {
      descriptionBuilder.addThreadOrLoopDescription(threadAndLoopDescriptionIter.next());
    }
    stream.close();
  }

}
