package com.anarsoft.race.detection.process.loadDescription

import com.anarsoft.race.detection.process.main.LoadDescription
import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.vmlens.trace.agent.bootstrap.description.{DeserializeClassDescriptions, DeserializeThreadAndLoopDescription}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.{DESCRIPTION, THREAD_AND_LOOP_DESCRIPTION}
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX

import java.io.DataInputStream
import java.nio.file.{Files, Path}

class LoadDescriptionImpl(dir: Path) extends LoadDescription {

  override def load(descriptionBuilder: DescriptionBuilder): Unit = {
    loadClassDescription(descriptionBuilder);
    loadThreadAndLoopDescription(descriptionBuilder);
  }
  
  def hasThreadAndLoopDescription() : Boolean = 
    dir.resolve(THREAD_AND_LOOP_DESCRIPTION + EVENT_FILE_POSTFIX).toFile.exists()
  

  private def loadClassDescription(descriptionBuilder: DescriptionBuilder): Unit = {
    val stream = new DataInputStream(Files.newInputStream(dir.resolve(DESCRIPTION + EVENT_FILE_POSTFIX)))
    val deserializeClassDescriptions = new DeserializeClassDescriptions();
    val classDescriptionIter = deserializeClassDescriptions.deserialize(stream).iterator();

    while (classDescriptionIter.hasNext) {
      descriptionBuilder.addClassDescription(classDescriptionIter.next());
    }
    stream.close();
  }

  private def loadThreadAndLoopDescription(descriptionBuilder: DescriptionBuilder): Unit = {
    val stream = new DataInputStream(Files.newInputStream(dir.resolve(THREAD_AND_LOOP_DESCRIPTION + EVENT_FILE_POSTFIX)))
    val deserializeThreadAndLoopDescription = new DeserializeThreadAndLoopDescription();
    val threadAndLoopDescriptionIter = deserializeThreadAndLoopDescription.deserialize(stream).iterator();

    while (threadAndLoopDescriptionIter.hasNext) {
      descriptionBuilder.addThreadOrLoopDescription(threadAndLoopDescriptionIter.next());
    }
    stream.close();
  }

}
