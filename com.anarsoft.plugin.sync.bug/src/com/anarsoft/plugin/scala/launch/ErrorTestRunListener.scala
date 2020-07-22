package com.anarsoft.plugin.scala.launch

import org.eclipse.jdt.junit.TestRunListener
import org.eclipse.jdt.junit.model.ITestRunSession
import org.eclipse.jdt.junit.model.ITestElement.Result.ERROR;
import org.eclipse.jdt.junit.model.ITestElement.Result.FAILURE
import com.anarsoft.plugin.sync.bug.Activator


class ErrorTestRunListener  extends TestRunListener {
  
  @volatile var hasErrors = false;
  
  
  
  override def  sessionFinished( session : ITestRunSession)
  {
    val result = session.getTestResult(true);
    
    Activator.plugin.debug(  result.toString() + " " + session.getTestRunName )
    
    if( ERROR.equals(result) ||  FAILURE.equals(result) )
    {
      hasErrors = true
    }
    else
    {
      hasErrors = false;
    }
    
  }
}