/* NOTE this file is autogenerated by Scalate : see http://scalate.fusesource.org/ */
package templates

import _root_.scala.collection.JavaConversions._
import _root_.org.fusesource.scalate.support.TemplateConversions._
import _root_.org.fusesource.scalate.util.Measurements._

object $_scalate_$htmlLastRun_mustache {
  def $_scalate_$render($_scalate_$_context: _root_.org.fusesource.scalate.RenderContext): Unit = {
    ;{
      val context: _root_.org.fusesource.scalate.RenderContext = $_scalate_$_context.attribute("context")
      import context._
      
      
      import _root_.org.fusesource.scalate.mustache._
      
      val $_scope_1 = Scope($_scalate_$_context)
      $_scope_1.partial("header")
      $_scalate_$_context << ( "\r\n\r\n<!-- htmlLastRun.mustache -->\r\n\r\n<div class=\"" )
      $_scope_1.renderVariable("containerTyp", false)
      $_scalate_$_context << ( "\">\r\n \r\n\r\n \r\n\r\n \r\n \r\n<div class=\"row text-left\">\r\n  \r\n  \r\n <table class=\"table table-striped\" >\r\n  <tr>\r\n  \t<th>*</th>\r\n    <th>Operation</th>\r\n  \r\n    <th>Method</th>\r\n    <th>Thread Id</th>\r\n   \r\n  </tr>\r\n  \r\n  \r\n  \r\n" )
      $_scope_1.section("issues") { $_scope_2 =>
        $_scope_2.section("statement") { $_scope_3 =>
          $_scalate_$_context << ( "<!-- skipAtCompare -->    <tr  " )
          $_scope_3.renderVariable("trStyle", true)
          $_scalate_$_context << ( ">\r\n\r\n<td>\r\n</td>\r\n<!-- skipAtCompare -->  <td> <a   href=\"" )
          $_scope_3.renderVariable("link", false)
          $_scalate_$_context << ( "\" > \r\n   <img src=\"" )
          $_scope_3.renderVariable("root", false)
          $_scope_3.renderVariable("imagePath", true)
          $_scalate_$_context << ( "\" />  " )
          $_scope_3.renderVariable("operation", true)
          $_scalate_$_context << ( " \r\n<!-- skipAtCompare --> " )
          $_scope_3.renderVariable("objectId", false)
          $_scalate_$_context << ( " \r\n </a>\r\n</td>\r\n \r\n <!-- skipAtCompare -->     <td>    " )
          $_scope_3.section("hasLink") { $_scope_4 =>
            $_scalate_$_context << ( "<a   href=\"" )
            $_scope_4.renderVariable("link", false)
            $_scalate_$_context << ( "\" > \r\n" )
            $_scope_4.renderVariable("method", true)
            $_scalate_$_context << ( " </a> " )
          }
          $_scope_3.invertedSection("hasLink") { $_scope_5 =>
            $_scope_5.renderVariable("method", true)
            $_scalate_$_context << ( " " )
          }
          $_scalate_$_context << ( "</td>\r\n   \r\n   \r\n  \r\n  \r\n<!-- skipAtCompare -->   <td>" )
          $_scope_3.renderVariable("threadId", false)
          $_scalate_$_context << ( "</td>\r\n   \r\n  </tr>\r\n  \r\n" )
        }
        $_scope_2.section("loop") { $_scope_6 =>
          $_scalate_$_context << ( "<!-- skipAtCompare -->    <tr  " )
          $_scope_6.renderVariable("trStyle", true)
          $_scalate_$_context << ( ">\r\n\r\n<td " )
          $_scope_6.renderVariable("tdStyle", true)
          $_scalate_$_context << ( "  >" )
          $_scope_6.renderVariable("loopCount", false)
          $_scalate_$_context << ( "</td>\r\n\r\n" )
          $_scope_6.section("list") { $_scope_7 =>
            $_scope_7.section("needsTrStart") { $_scope_8 =>
              $_scalate_$_context << ( "<!-- skipAtCompare -->    <tr  " )
              $_scope_8.renderVariable("trStyle", true)
              $_scalate_$_context << ( ">\r\n" )
            }
            $_scalate_$_context << ( "<!-- skipAtCompare -->  <td " )
            $_scope_7.renderVariable("tdStyle", true)
            $_scalate_$_context << ( "  >  <a   href=\"" )
            $_scope_7.renderVariable("link", false)
            $_scalate_$_context << ( "\" > \r\n   <img src=\"" )
            $_scope_7.renderVariable("root", false)
            $_scope_7.renderVariable("imagePath", true)
            $_scalate_$_context << ( "\" />  " )
            $_scope_7.renderVariable("operation", true)
            $_scalate_$_context << ( " \r\n<!-- skipAtCompare --> " )
            $_scope_7.renderVariable("objectId", false)
            $_scalate_$_context << ( " \r\n </a>\r\n</td>\r\n\r\n \r\n <!-- skipAtCompare -->     <td " )
            $_scope_7.renderVariable("tdStyle", true)
            $_scalate_$_context << ( "  >    " )
            $_scope_7.section("hasLink") { $_scope_9 =>
              $_scalate_$_context << ( "<a   href=\"" )
              $_scope_9.renderVariable("link", false)
              $_scalate_$_context << ( "\" > \r\n" )
              $_scope_9.renderVariable("method", true)
              $_scalate_$_context << ( " </a> " )
            }
            $_scope_7.invertedSection("hasLink") { $_scope_10 =>
              $_scope_10.renderVariable("method", true)
              $_scalate_$_context << ( " " )
            }
            $_scalate_$_context << ( "</td>\r\n   \r\n   \r\n  \r\n  \r\n<!-- skipAtCompare -->   <td " )
            $_scope_7.renderVariable("tdStyle", true)
            $_scalate_$_context << ( "  >" )
            $_scope_7.renderVariable("threadId", false)
            $_scalate_$_context << ( "</td>\r\n   \r\n  </tr>\r\n  \r\n" )
          }
        }
      }
      $_scalate_$_context << ( "</table>   \r\n    \r\n     \r\n</div> \r\n \r\n \r\n \r\n\r\n\r\n\r\n</div>\r\n\r\n\r\n\r\n\r\n" )
      $_scope_1.partial("footer")
    }
  }
}


class $_scalate_$htmlLastRun_mustache extends _root_.org.fusesource.scalate.Template {
  def render(context: _root_.org.fusesource.scalate.RenderContext): Unit = $_scalate_$htmlLastRun_mustache.$_scalate_$render(context)
}
