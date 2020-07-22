package com.anarsoft.plugin.view.internalView

import org.eclipse.swt.browser.Browser

class BrowserView(val browser : Browser) extends InternalView {
  
    def dispose()
    {
      browser.dispose();
    }
    
    
  def setFocus()
  {
      browser.setFocus();
  }
  
  
}