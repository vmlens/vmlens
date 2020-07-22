package com.anarsoft.integration

import scala.collection.mutable.ArrayBuffer;
import net.liftweb.json._;
import java.io._;
import org.apache.commons.io._;

class CopyDescription(  val targets : List[CopyTarget]) {
  
  

  
  
}

object CopyDescription
{
  
    implicit val formats = DefaultFormats
  
    def main(args : Array[String])
  {
    val dataStream = new FileInputStream(new File("/home/thomas/git-repo/workspace/test-regression-maven/src/test/resources/Array@com_vmlens_test_regression_TestAllTypes_testArrayAccess.json"));
    val contents =    IOUtils.toString(dataStream);
    val json = parse( contents );
    
    val elem =  json.extract[CopyTarget] 
    
  }
  
  
}