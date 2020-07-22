package com.anarsoft.plugin.view.tree

import org.eclipse.jface.viewers._;
import  com.vmlens.api.internal.reports.Element4TreeViewer
import com.anarsoft.race.detection.model.result.SearchData
import  com.anarsoft.plugin.view.SourceCodeSearchJob;


class OpenDeclarationOnSelection extends ISelectionChangedListener {

  def selectionChanged(event: SelectionChangedEvent) {

    val selection = event.getSelection();

    if (selection != null) {
      if (! selection.isEmpty()) {

        if (selection.isInstanceOf[IStructuredSelection]) {

          val firstElement = selection.asInstanceOf[IStructuredSelection].getFirstElement;

           val element4TreeViewer = firstElement.asInstanceOf[Element4TreeViewer]

           
           element4TreeViewer.searchData()  match
           {
             case None =>
               {
                 
               }
            
             case Some(data) =>
               {
                 openDeclaration(data);
               }
             
             
           }
           
            

          }

        }

      }

    }

  
   def openDeclaration(searchData : SearchData)
   {
      val job =  new  SourceCodeSearchJob(  searchData );
     job.schedule();
   }
  
  
  
  
  
  

}