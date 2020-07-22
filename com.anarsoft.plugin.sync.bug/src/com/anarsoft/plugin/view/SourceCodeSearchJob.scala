package com.anarsoft.plugin.view

import org.eclipse.ui.PlatformUI;
import com.anarsoft.race.detection.model._;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.TypeNameMatchRequestor;
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.core.ISourceReference
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core._;
import com.anarsoft.plugin.sync.bug.Activator;
import scala.collection.mutable.HashSet;
import com.anarsoft.plugin.view.wrapper._;
import com.anarsoft.race.detection.model.result._;
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.IStatus
import org.eclipse.jface.dialogs.MessageDialog;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.integration.TextRepository
import com.anarsoft.race.detection.model.result._;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.BadLocationException;


class SourceCodeSearchJob(val searchData: SearchData) extends Job("Searching for " + searchData.fullQualifiedClassName) {

  def run(monitor: IProgressMonitor) =
    {

      var t: IType = null;

      val requestor = new TypeNameMatchRequestor() {
        def acceptTypeNameMatch(m: TypeNameMatch) {

          val newTyp = m.getType();

          if (t == null) {
            t = newTyp;
          }

          if (newTyp.isInstanceOf[ISourceReference] && newTyp.isInstanceOf[IFile]) {

            if (!isClassFile(newTyp.asInstanceOf[IFile])) {
              t = newTyp;

            }

          }

          // Activator.plugin.debug(t.getClass().toString());

        }
      };

      // Activator.plugin.debug("search for " + searchFor.getPackageName() + " "  + searchFor.getSimpleClassName());

      new SearchEngine().searchAllTypeNames(
        searchData.packageName.toCharArray(),
        SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE,
        searchData.className.toCharArray(),
        SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE,
        IJavaSearchConstants.CLASS,
        SearchEngine.createWorkspaceScope(),
        requestor, IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, monitor);

      if (t == null) {
        val message = TextRepository.SOURCE_NOT_FOUND + searchData.fullQualifiedClassName;
        //
        //       Activator.plugin.execWithDisplay( d =>
        //         {
        //           MessageDialog.openWarning(  d.getActiveShell  , "Code Search Warning", message);
        //
        //         });

        new Status(IStatus.WARNING, Activator.PLUGIN_ID, 1, message, null);
      } else {

        Activator.plugin.execInUIThread { () =>

          {
            searchData.searchDataInClass match {
              case SearchDataInClassField(fieldName) =>
                {
                  JavaUI.openInEditor(t.getField(fieldName), true, true);
                }

              case SearchDataLineNumber(lineNumber) =>
                {
                  val editor = JavaUI.openInEditor(t, true, false);

                  if (lineNumber >= 0) {
                    try {
                      if ((editor.isInstanceOf[ITextEditor])) {

                        val textEditor = editor.asInstanceOf[ITextEditor];

                        val document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
                        textEditor.selectAndReveal(document.getLineOffset(lineNumber - 1), document.getLineLength(lineNumber - 1));
                      }

                    } catch {

                      case exp: BadLocationException =>
                        {
                          // marker refers to invalid text position -> do nothing
                        }

                    }

                  }
                }

            }

          }

        };

        Status.OK_STATUS;
      }

    }

  def isClassFile(file: IFile) =
    {
      try {

        val contentType = file.getContentDescription().getContentType()

        if (contentType == null) {
          false;
        } else {
          "org.eclipse.jdt.core.javaClass".equals(contentType.getId())
        }

      } catch {

        case e: Exception =>
          {
            false;
          }

      }
    }

}