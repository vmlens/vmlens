package com.anarsoft.plugin.view.wrapper
import org.eclipse.core.runtime.Status;
import com.anarsoft.plugin.sync.bug.TestActivator;
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.browser.Browser
import org.eclipse.ui.forms.widgets._;
import org.eclipse.swt.graphics._;
import org.eclipse.swt.widgets._;
import org.eclipse.swt.layout._
import org.eclipse.jface.viewers._;
import java.util._;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets._;
import org.eclipse.jface.window.ToolTip;
import scala.collection.mutable.ArrayBuffer
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource._
import org.eclipse.swt.events._;
import com.anarsoft.plugin.sync.bug.Activator;
import org.eclipse.ui.part.ViewPart
import org.eclipse.jface.resource.JFaceResources;
import com.anarsoft.plugin.view._;
import com.anarsoft.race.detection.model._;
import com.anarsoft.plugin.view.action._;
import com.anarsoft.plugin.view._;
import org.eclipse.jface.action._;
import scala.collection.JavaConverters._
import org.eclipse.core.runtime.IStatus
import com.vmlens.api.internal.IconRepository;
import com.vmlens.api._;



class IssueViewWrapper extends ViewPartTemplate {
  
  def id = Activator.ISSUE_VIEW_ID;
   
  
    def viewData  =  Activator.plugin.viewManager.viewState.issueViewData;
  
  
}


