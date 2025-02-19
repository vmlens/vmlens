package com.anarsoft.race.detection.process.loadAgentLog

import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository.AGENTLOG
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX
import com.vmlens.trace.agent.bootstrap.event.warning.DeserializeInfoMessageEvent

import java.io.{DataInputStream, PrintStream}
import java.nio.file.{Files, Path}

class LoadAgentLog(dir: Path) {

  def load(output: PrintStream): Unit = {
    val stream = new DataInputStream(Files.newInputStream(dir.resolve(AGENTLOG + EVENT_FILE_POSTFIX)))
    val deserializeInfoMessageEvent = new DeserializeInfoMessageEvent();
    val messageList = deserializeInfoMessageEvent.deserialize(stream);
    messageList.forEach(message => message.writeToStream(output));
  }

}
