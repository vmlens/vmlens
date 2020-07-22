package com.vmlens.api.callback

import com.vmlens.api._;
import java.io.File;
import com.anarsoft.config.MavenMojo;
import com.vmlens.maven.plugin.Extended
import com.anarsoft.race.detection.process.ReadAndProcessEvents;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.process.workflow.ProgressMonitor;
import net.liftweb.json._;
import net.liftweb.json.Extraction._
import java.io._;
import com.vmlens.trace.agent.bootstrap.typeDesc._
import com.vmlens.maven.plugin.Group
import scala.collection.mutable.HashMap
import com.vmlens.api.internal.reports.CreateParallizedReportAlgo

abstract class ApiCallbackParallizeAbstract extends APICallback {

  def onProcessing(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo);
  def onSuccess(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo);
  def onIssueFound(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo);
  def onTestFailure(runResult: ModelFacadeAll, source: String, mavenMojo: MavenMojo);
  
  def isTest : Boolean;
  def ignoreIssues() : Boolean;
  
  def execOneRun(source: String, progressMonitor: ProgressMonitor, mavenMojo: MavenMojo) =
    {

      val readAndProcessEvents = 
        if(isTest)
        {
           ReadAndProcessEvents.create4Test(mavenMojo);
        }
        else
        {
            ReadAndProcessEvents.create4Prod(mavenMojo);
        }
        
       
      val result = readAndProcessEvents.prozessMavenPlugin(source, progressMonitor);

      // || isTest 
      
      if (mavenMojo.getAgentLog() || mavenMojo.getAgentLogPerformance()  || isTest  ) {

        val agentLogStream = new PrintStream(mavenMojo.getReportDir() + "/agentLog");

        for (m <- result.agentLog) {
          agentLogStream.println(m);

        }

        agentLogStream.close();

      }

      result;
    }

  @throws(classOf[IssuesFoundException])
  def prozess(source: String, mavenMojo: MavenMojo, progressMonitor: ProgressMonitor) =
    {
      val result = execOneRun(source, progressMonitor, mavenMojo);

      if (result.hasIssues()) {
        onIssueFound(result, source, mavenMojo)

        if (!ignoreIssues()) {
          throw new IssuesFoundException("There are race conditions and/or deadlocks in the test run. \n" +
            "Please see " + mavenMojo.getReportDir() + "/issues.html for the details.")
        } else {
          false;
        }

      } else {

        onSuccess(result, source, mavenMojo)

        false;

      }

    }

  def prozessTestError(source: String, mavenMojo: MavenMojo, progressMonitor: ProgressMonitor) {
    val result = execOneRun(source, progressMonitor, mavenMojo);

    onTestFailure(result, source, mavenMojo);
  }

}