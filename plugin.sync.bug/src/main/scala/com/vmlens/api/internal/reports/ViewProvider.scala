package com.vmlens.api.internal.reports


// val create : ( String , String ) =>  ReportView 

class ViewProvider( val templateName : String,val fileName : String,val viewData : ViewData ) {
 
  
  def view() = 
  {
    
    /*
     *  new ReportView( linkedElements , Nil ,   name   ,linkText   ,  templateName ,"../" , contextReport  );
     * ( val elements : Seq[ReportElement]  , val warnings : Seq[ReportText], title : String,val fileName : String ,  templateName : String, root : String, val contextReport : ContextReport  )
     * 
     */
    
    //  ReportView( val elements : Seq[ReportElement]  , val warnings : Seq[ReportText], title : String,val fileName : String ,  templateName : String, root : String, val contextReport : ContextReport  )
    
   
    
    
     new ReportView( viewData.elements , viewData.warnings ,   viewData.name   ,  viewData.titlePrefix ,  fileName   ,  templateName ,viewData.root, viewData.context  );
    
    
  }
  
}