package com.anarsoft.plugin.sync.bug

import com.google.common.base.Splitter;
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.osgi.framework.BundleContext
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.eclipse.debug.core._;
import com.anarsoft.plugin.scala.launch._;
import java.io._;
import scala.collection.mutable.ArrayBuffer
import org.eclipse.jface.resource.ImageRegistry;
import com.anarsoft.plugin.view._;
import org.osgi.framework.Bundle;
import com.anarsoft.plugin.sync.bug.startup._;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.IStatus
import java.util.concurrent._;
import scala.collection.mutable.HashSet;
import java.util.Properties
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IViewSite
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT
import org.eclipse.jface.action._;
import com.anarsoft.plugin.view._;
import org.eclipse.swt.widgets.Display
import org.eclipse.jface.viewers._;
import org.objectweb.asm.Opcodes;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.ui.ISelectionListener
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Shell
import com.vmlens.api.MemoryAccessType;
import com.anarsoft.integration._;
import org.eclipse.jface.window.Window;
import org.eclipse.core.runtime.Status;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.IDialogConstants;
import com.anarsoft.plugin.preferences.PluginKeys
import com.vmlens.api.internal._;
import com.vmlens.api._;
import org.eclipse.ui.part.ViewPart

/**
 *
 *
 * for launch description: http://www.eclipse.org/articles/Article-Launch-Framework/launch.html
 *
 *
 * f�r marker editor
 * http://stackoverflow.com/questions/2985307/how-to-extend-the-eclipse-java-editor-to-add-coloured-overlays
 *
 * f�r editor:
 * http://www.vogella.de/articles/EclipseEditors/article.html
 * http://stackoverflow.com/questions/786884/tutorial-regarding-the-development-of-a-custom-eclipse-editor
 *
 *
 * speichern der monitor und trigger
 * http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Fguide%2FresAdv_saving.htm
 * http://wiki.eclipse.org/FAQ_Where_do_plug-ins_store_their_state%3F
 *
 *
 *
 *
 *
 * import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
 * import org.eclipse.debug.ui.CommonTab;
 * import org.eclipse.debug.ui.EnvironmentTab;
 * import org.eclipse.debug.ui.ILaunchConfigurationDialog;
 * import org.eclipse.debug.ui.ILaunchConfigurationTab;
 * import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;
 * import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
 * import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
 * import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab;
 * import org.eclipse.jdt.debug.ui.launchConfigurations.JavaMainTab;
 *
 * public class LocalJavaApplicationTabGroup extends AbstractLaunchConfigurationTabGroup
 * {
 * public void createTabs(ILaunchConfigurationDialog dialog, String mode)
 * {
 * ILaunchConfigurationTab[] tabs = {
 * new JavaMainTab(),
 * new JavaArgumentsTab(),
 * new JavaJRETab(),
 * new JavaClasspathTab(),
 * new SourceLookupTab(),
 * new EnvironmentTab(),
 * new CommonTab() };
 *
 * setTabs(tabs);
 * }
 * }
 *
 *
 *
 *
 */

class Activator extends AbstractUIPlugin with CopyFilesToRuntimeDir  {

  
  def transformLicenseText(license : String) =
  {
       
   val split = Splitter.fixedLength(30).split(license);

   
//   val prefix = "$email_text .= '"
//   val postfix = """' . "\n";""";
   
  var transformed = "";
   
   val it = split.iterator()
   
   while( it.hasNext() )
   {
     transformed  =   transformed + it.next() +  System.lineSeparator() ;
   }
   
   transformed;
  }
 

var viewManager : ViewManager = null;
  
  
  
	 
  override def start(context: BundleContext) {
    super.start(context);

    Activator.plugin = this;
    


    copyJarFiles();

    var openStartWindow = false;

    OnStartup.startFileListenerIfNotStarted();
    
    
     DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(new VmlensLaunchConfigurationListener() );
     
     HttpServer.checkAndStart();
     
     
       
       
        
     viewManager = new ViewManager();
     
      //  Activator.plugin.getPreferenceStore().setValue(name, value)
        
  }

  /*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */


 
  

  override def initializeImageRegistry(registry: ImageRegistry) {
    super.initializeImageRegistry(registry);
    val bundle = Platform.getBundle(Activator.PLUGIN_ID);

    IconRepository.foreachIcon { x =>  
      
      x match
      {
        case SingleIcon(path) =>
          {
            addSingleImage(path, registry, bundle);
          }
        
        case CompositeIcon( baseIcon : Icon , overlayIcon : Icon, position : Int)  =>
          {
            
        registry.put (
      x.getId()  , 
       
      new DecorationOverlayIcon( registry.get( baseIcon.getId() ) ,registry.getDescriptor( overlayIcon.getId()  ),  position ) );
          }
          
        
      }
    
    
    }

  }

  

  def addSingleImage(id: String, registry: ImageRegistry, bundle: Bundle) {
    registry.put(id, ImageDescriptor.createFromURL(
      FileLocator.find(bundle,
        new Path(id),
        null)));

  }

   
  
  
  
  
   def showView(viewId: String) {
    val workbench = PlatformUI.getWorkbench();

    workbench.getDisplay().asyncExec(new Runnable() {
      def run() {
        val window = workbench.getActiveWorkbenchWindow();
        if (window != null) {
          window.getActivePage().showView(viewId)
        }
      }
    });
  }
   
   
   def execWithDisplay(f: Display => Unit) {
    val workbench = PlatformUI.getWorkbench();

    workbench.getDisplay().asyncExec(new Runnable() {
      def run() {

        f(workbench.getDisplay());

      }
    });

  }
   
   
   
       def execWithShell(f: Shell => Unit) {
    val workbench = PlatformUI.getWorkbench();

    workbench.getDisplay().asyncExec(new Runnable() {
      def run() {

        val win = workbench.getActiveWorkbenchWindow();
        
        val shell = 
          
          if( win != null )
           {
            win.getShell()
          }
          else
          {
            null;
          }
        
        
        
        f(shell);

      }
    });

  }
   
   
   
   
   
   
   
   

   def execInUIThread(f: () => Unit) {
    val workbench = PlatformUI.getWorkbench();

    workbench.getDisplay().asyncExec(new Runnable() {
      def run() {

        f();

      }
    });

  }
   
   
   
     def log( e : Throwable) {
      getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.ERROR, "error in vmlens", e));
   }
     
      def debug( e : String) {
     // getLog().log(new Status(IStatus.INFO, Activator.PLUGIN_ID,  e ));
   }
   
     
     
    def attachMenuBarToForm(form: ViewPart, shellForTest : Shell, callback : IToolBarManager  => Unit) {
   

    val actionBars = form.getViewSite().getActionBars();

    //val dropDownMenu = actionBars.getMenuManager();
    val toolBar = actionBars.getToolBarManager();

    callback(toolBar);
    
   // wrapper.addActionsTheToolBarManager(toolBar,modelViewView);
  
    toolBar.update(true);
  }  
     
    
    def getSharedImage(name: String) =
    {
      // ISharedImages.IMG_TOOL_COPY
      getWorkbench().getSharedImages().getImageDescriptor(name);

    } 
    
     
  
   
}
object Activator {

 
  
  
 // val viewManager = new ViewManager();
 
  
  
  
  
  
  def agentString(propetyFileName : String) =
  {
    plugin.getJavaAgentString(propetyFileName);
  }
  
   def getImageDescriptor(id: Icon) =
    {
      plugin.getImageRegistry().getDescriptor(id.getId())
    }
  
      def getImage(id: Icon) =
    {
       val image =  plugin.getImageRegistry().get(id.getId());
       
       
       image;
    }
   
   
   
     def log( e : Throwable)
  {
    plugin.log(e);
  }
  
     
     


  //val LOGO_SMALL  = "icons/logo_small.png";

  //val LOGO_ISSUE_VIEW = "icons/logo_issue_view.png";

  val PLUGIN_ID = "com.anarsoft.plugin.sync.bug";
  @volatile var plugin: Activator = null;

 

//  val START_VIEW_ID = "com.anarsoft.plugin.scala.view.VMLensStartView";
  val ISSUE_VIEW_ID = "com.anarsoft.plugin.scala.view.VMLensDataRaceView";
  val ELEMENT_VIEW_ID = "com.anarsoft.plugin.view.StackTraceViewWrapper";
 
  
  
  
  //  val METHOD_DETAIL_VIEW_ID = "com.anarsoft.plugin.view.wrapper.AccessViewViewWrapper";
  
  
  
  
  
  

}


