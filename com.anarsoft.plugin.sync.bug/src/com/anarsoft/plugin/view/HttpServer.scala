package com.anarsoft.plugin.view

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.anarsoft.plugin.sync.bug.Activator
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.http.jetty.JettyConfigurator;
import org.eclipse.equinox.http.jetty.JettyConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;


class HttpServer {
  
  var port = -1;
  var alreadyStarted = false;
  
  val  AUTO_SELECT_JETTY_PORT = 0;
  
  
  val getOtherInfo = "com.vmlens"; 
  
  
  def checkBundleAndGetPort()  {
		val bundle = Platform.getBundle("org.eclipse.equinox.http.registry"); //$NON-NLS-1$
		if (bundle == null) {
			throw new BundleException("org.eclipse.equinox.http.registry"); //$NON-NLS-1$
		}
		if (bundle.getState() == Bundle.RESOLVED) {
			bundle.start(Bundle.START_TRANSIENT);
		}
		if (port == -1) {
			// Jetty selected a port number for us "(other.info=" + getOtherInfo+ ')'
			val reference = bundle.getBundleContext().getServiceReferences("org.osgi.service.http.HttpService", "(other.info=" + getOtherInfo+ ')'); //$NON-NLS-1$ //$NON-NLS-2$
			
//			for( elem <- reference )
//			{
//			  	 Activator.plugin.debug("bundle " + elem.getBundle.getSymbolicName)
//			  
//			  for( key <- elem.getPropertyKeys )
//			  {
//			    	 Activator.plugin.debug(key  + "  " + elem.getProperty(key))
//			  }
//			}
			
			
			val assignedPort = reference(0).getProperty("http.port"); //$NON-NLS-1$
			port = Integer.parseInt(assignedPort.asInstanceOf[String]);
		}
	}
  
  
  
  
  
  
  
  
  
   def checkAndStart()
  {
    this.synchronized
    {
    if(! alreadyStarted)
    {
      	val d = new Hashtable[String,Object]();
				val SESSION_TIMEOUT_INTERVAL_IN_SECONDS = 30*60;  // 30 minutes
		
				d.put("http.port",new Integer(AUTO_SELECT_JETTY_PORT) ); //$NON-NLS-1$

				// set the base URL
				d.put("context.path","/"); //$NON-NLS-1$
				d.put("other.info", getOtherInfo); //$NON-NLS-1$
				d.put(JettyConstants.CONTEXT_SESSIONINACTIVEINTERVAL, new Integer(SESSION_TIMEOUT_INTERVAL_IN_SECONDS) );

				// suppress Jetty INFO/DEBUG messages to stderr
				Logger.getLogger("org.mortbay").setLevel(Level.WARNING); //$NON-NLS-1$

				

				JettyConfigurator.startServer("vmlens", d);
				
				 checkBundleAndGetPort() ;
				 Activator.plugin.debug("port " + port)
//				
//				val iter = d.entrySet().iterator();
//				
//				
//				
//				
//				while(  iter.hasNext() )
//				{
//				  val c = iter.next();
//				  
//				
//				  
//				  Activator.plugin.debug(c.getKey + " " + c.getValue )
//				  
//				}
//				
//				
				
				
				alreadyStarted = true;
    }
    }
  }
  
  
}

object HttpServer
{
  val instance = new HttpServer();
  
  
  def checkAndStart()
  {
    instance.checkAndStart();
  }
  
  
  
}




