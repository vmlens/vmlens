package com.anarsoft.plugin.view.launch

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import com.anarsoft.plugin.sync.bug.SWTFactory;
import com.anarsoft.plugin.sync.bug.Activator$;
import org.eclipse.debug.core.ILaunchManager;
import java.util.Map;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.debug.ui._
import com.anarsoft.plugin.view.factory._;
import com.anarsoft.plugin.sync.bug.Activator
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import java.util.StringTokenizer
import org.eclipse.swt.widgets.List;
import com.vmlens.api.internal.IconRepository
import com.anarsoft.plugin.preferences.PluginKeys
import com.anarsoft.plugin.preferences.PluginDefaultValues
import com.anarsoft.trace.agent.runtime.util.AgentKeys
/**
 * 
 * config für runner:
	modi
	do not trace
	trace
 * 
 */



class VMLensLaunchTab  extends AbstractLaunchConfigurationTab {
  
    val modeName2Id = new HashMap[String,Int]
    val id2ModeName = new HashMap[Int,String]
    
    
    modeName2Id.put("interleave" , 0);
    modeName2Id.put("state" , 1);
    modeName2Id.put("monitor" , 2);
  
    id2ModeName.put(0 , "interleave" );
    id2ModeName.put(1 , "state");
    id2ModeName.put(2 , "monitor");
    
    
    
     def parseString( stringList: String) =
    {
        
    	 val list = new ArrayBuffer[String];
        
    	  val st = new StringTokenizer(stringList , ";");
        while (st.hasMoreTokens()) {
           
        	val current = st.nextToken();
        	
        	if( ! current.equals("") ) 
        	{
        		list.append( current);
        	}
        	
         }
        
       list;
      }
    
    
    
    
    
    
    
    
    
     def createStringFromArray(items : Array[String]) =
{
  var result = "";
  var isFirst = true;
  
  for( elem <- items )
  {
    if( ! isFirst)
    {
      result = result + ";"; 
    }
    
    isFirst = false;
    
     result = result + elem;
    
    
  }
  
   result;
}
  
  def fillList(string : String , list : List)
  {
    
    list.removeAll();
    
   for( elem <- parseString(string) )
   {
     list.add(elem);
   }
  }
     
     
     
    var launchTabInternal : Option[LaunchTabInternal] = None;
  
  
     def onViewChanged()
     {
       	 Activator.plugin.debug("onViewChanged ")
       
       updateLaunchConfigurationDialog();
     }
  
  
     def initializeFrom(conf: ILaunchConfiguration)
     {
        val mode = conf.getAttribute(   AgentKeys.MODE ,PluginDefaultValues.MODE );
        val trace = conf.getAttribute(   AgentKeys.TRACE ,   PluginDefaultValues.TRACE  );
        val listDoNotTraceIn =  conf.getAttribute(   AgentKeys.DO_NOT_TRACE_IN ,PluginDefaultValues.DO_NOT_TRACE_IN );
        val excludeFromStackTrace =  conf.getAttribute(   AgentKeys.EXCLUDE_FROM_TRACE ,PluginDefaultValues.EXCLUDE_FROM_TRACE );
        val suppress =  conf.getAttribute(   AgentKeys.SUPPRESS ,PluginDefaultValues.SUPPRESS );
       
       
        launchTabInternal match
        {
          
          case None =>
            {
              
            }
          
          case Some(tab) =>
            {
                  val id = modeName2Id.get(mode).get;
                   tab.combo.select(id);
              
              
              fillList(trace , tab.listTrace  );
              fillList(listDoNotTraceIn , tab.listDoNotTraceIn);
              fillList(excludeFromStackTrace , tab.excludeFromStackTrace  );
              fillList(suppress , tab.suppress);
              
              
            }
            
          
          
        }
        
        
        
        
        
     }
     
     
     def setListValues(configKey : String , list : List,conf : ILaunchConfigurationWorkingCopy)
     {
       val s = createStringFromArray(list.getItems());
        conf.setAttribute( configKey , s );
          
       
     }
     
  
  
     def performApply(conf : ILaunchConfigurationWorkingCopy )
     {
        Activator.plugin.debug("performApply ")
        
           launchTabInternal match
        {
          
          case None =>
            {
              
            }
          
          case Some(tab) =>
            {
              val id = tab.combo.getSelectionIndex();
              val mode = id2ModeName.get(id).get;
              
               conf.setAttribute(  AgentKeys.MODE , mode );
              
               setListValues(   AgentKeys.TRACE  , tab.listTrace , conf );
               setListValues(   AgentKeys.DO_NOT_TRACE_IN  , tab.listDoNotTraceIn , conf);
               setListValues(   AgentKeys.EXCLUDE_FROM_TRACE  , tab.excludeFromStackTrace , conf );
               setListValues(   AgentKeys.SUPPRESS  , tab.suppress , conf);
            }
        }
        
     }
     
     
     def setDefaults(conf : ILaunchConfigurationWorkingCopy)
     {
       
       VMLensLaunchTab.setDefaults(conf);
     }
     
     
     def createControl( parent : Composite)
    {
        val comp = new Composite(parent, 0);
//        comp.setLayout(new GridLayout(1, true));
        comp.setFont(parent.getFont());
        super.setControl(comp);
        createLaunchComponent(comp);
    }
  
  
    def createLaunchComponent( parent : Composite)
    {
     launchTabInternal = Some( FactoryLaunchTabInternal.create(parent, onViewChanged))
    }
     
    
   def getName() = "vmlens";
     

   
   override def getImage() =  Activator.getImage(IconRepository.LOGO_RED);
    
    
    
    //Activator$.MODULE$.getImageForId("icons/eview16/logo_start_view.png");
     
     
   
}

object VMLensLaunchTab
{
  
   def setDefaults(conf : ILaunchConfigurationWorkingCopy)
   {
     conf.setAttribute(  AgentKeys.MODE ,PluginDefaultValues.MODE  );
       conf.setAttribute(  AgentKeys.TRACE ,  PluginDefaultValues.TRACE    );
       conf.setAttribute(  AgentKeys.DO_NOT_TRACE_IN ,  PluginDefaultValues.DO_NOT_TRACE_IN  );
        conf.setAttribute(  AgentKeys.EXCLUDE_FROM_TRACE ,  PluginDefaultValues.EXCLUDE_FROM_TRACE  );
       conf.setAttribute(  AgentKeys.SUPPRESS ,  PluginDefaultValues.SUPPRESS  );
       
       conf.setAttribute(  PluginKeys.PROPERTY_FILE , System.currentTimeMillis() + ".properties"  );
   }
  
  
}




