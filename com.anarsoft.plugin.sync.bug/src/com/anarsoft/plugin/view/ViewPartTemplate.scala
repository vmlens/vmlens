package com.anarsoft.plugin.view

/**
 *   für refresh siehe: https://stackoverflow.com/questions/13234090/how-to-update-refresh-a-view-in-an-eclipse-plug-in
 *
 */

import org.eclipse.swt.widgets.Composite
import org.eclipse.ui.part.ViewPart
import com.anarsoft.plugin.sync.bug._;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import com.anarsoft.plugin.view.factory._;
import com.anarsoft.plugin.view.internalView.InternalView

abstract class ViewPartTemplate extends ViewPart {

  var parentComposite: Composite = null;
  var internalView: InternalView = null;

  final def createPartControl(parent: Composite) {

 

    parentComposite = parent;

    internalView = ViewFactory.create(this, parent, viewData);

    Activator.plugin.viewManager.put(this);

  }

  def setFocus() {
    internalView.setFocus()
  }

  def refresh() {

    Activator.plugin.debug("refresh  " + id)

    Activator.plugin.execWithDisplay(
      display =>
        {
          internalView.dispose();

          internalView = ViewFactory.create(this, parentComposite, viewData);

            Activator.plugin.debug("internalView  " + internalView)
              Activator.plugin.debug("viewData  " + viewData)
            
          
         // parentComposite.pack();
          parentComposite.layout(true);
          
          parentComposite.redraw();
          
        })

    Activator.plugin.debug("refresh  " + id)

  }

  override final def dispose() {
    Activator.plugin.viewManager.remove(this);
    internalView.dispose();
  }

  //

  def id: String;
  def viewData: ViewData;

}

