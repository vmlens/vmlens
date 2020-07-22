package com.anarsoft.plugin.view.servlet

import javax.servlet.http._
import com.anarsoft.plugin.sync.bug.Activator


class ControlServlet  extends HttpServlet {
 
  override def  doGet( req : HttpServletRequest,resp : HttpServletResponse)
  {
    
    
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    
    
    
    
   Activator.plugin.viewManager.viewState.onlineData.write2Stream(  req.getRequestURI() , resp.getWriter());
    
    
    
    
    
    resp.getWriter.close();
    
  }
  
}