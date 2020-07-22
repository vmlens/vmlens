package com.anarsoft.plugin.view.action


import scala.collection.mutable.ArrayBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd._;
import com.anarsoft.plugin.sync.bug.Activator;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.DND;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.ISharedImages;
import  com.vmlens.api.internal.reports.Element4TreeViewer


class CopyAction( val viewModelList : ArrayBuffer[Element4TreeViewer])  extends Action("Copy") {
  
   setImageDescriptor( Activator.plugin.getSharedImage(ISharedImages.IMG_TOOL_COPY) );
   setDisabledImageDescriptor( Activator.plugin.getSharedImage(ISharedImages.IMG_TOOL_COPY_DISABLED) );
   
   
   
   /*
    * taken from 
    * http://www.vogella.com/tutorials/EclipseJFaceTableAdvanced/article.html#copy-data-to-the-system-clipboard
    * 
    */
   
     def copy(viewModelList : ArrayBuffer[Element4TreeViewer])  
   {
		
       var text = "";
       
       for( m <- viewModelList )
       {
         text = text + m.getText() +  System.getProperty("line.separator");
       }
       
       
  
		Activator.plugin.execWithDisplay(
		    
		 display =>
		   {
		     
		        val clipboard = new Clipboard(display);
		        val textTransfer = TextTransfer.getInstance();
		        
		        try{
		        	clipboard.setContents(Array (text) , Array(textTransfer));
		        } 
		        finally{
		          clipboard.dispose();
		        }
		        
		       
		   }
		
		
		
		);
	
	}
  
  
  
  override def run()
  {
    
    copy(viewModelList);
    
  }
  
  
  
}