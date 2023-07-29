package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.process.ProzessConfig
import com.anarsoft.race.detection.process.workflow.SingleStep

import java.io._
import scala.collection.mutable.ArrayBuffer


class StepReadAgentLog(val eventDir: String, val prozessConfig: ProzessConfig) extends SingleStep[ContextReadAgentLog] {


  def execute(context: ContextReadAgentLog) {
    val agentLog = new ArrayBuffer[String]();
    val dir = new File(eventDir);


    for (file <- dir.listFiles()) {
      if (file.getName().contains("agentLog")) {
        val in = new DataInputStream(new FileInputStream(file));


        try {
          while (true) {
            val message = in.readUTF();

            if (message.equals("throw exception")) {
              throw new Exception("test message");
            }

            agentLog.append(message)
          }

        }
        catch {
          case eof: java.io.EOFException => {}
        }
        finally {
          in.close();
        }
      }
    }

    context.agentLog = agentLog;
  }

  def desc = "";

}

object StepReadAgentLog {

  def main(args: Array[String]) {
    val context = new ContextReadAgentLog {

    }
    context.agentLog = new ArrayBuffer[String]();
    val step = new StepReadAgentLog("C:\\git-repo\\vmlens-addons\\test-regression-maven\\target\\vmlens-agent\\vmlens", null);
    step.execute(context);
    println("event log")
    for (elem <- context.agentLog) {
      println(elem);
    }

  }

}