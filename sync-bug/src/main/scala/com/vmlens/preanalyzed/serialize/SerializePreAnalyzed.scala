package com.vmlens.preanalyzed.serialize


import com.vmlens.preanalyzed.factory.PreAnalyzedFactory
import com.vmlens.preanalyzed.model._

import java.io.{DataOutputStream, FileOutputStream}

class SerializePreAnalyzed {

  def serialize(list: List[PreAnalyzed], dataOutputStream: DataOutputStream): Unit = {
    val packageOrClassList = new TransformToPackageOrClass().transform(list)
    dataOutputStream.writeInt(packageOrClassList.length);
    for (packageOrClass <- packageOrClassList) {
      packageOrClass.serialize(dataOutputStream);
    }
  }
}

object SerializePreAnalyzed {

  def main(args: Array[String]) = {
    val fileOutputStream = new FileOutputStream("agent-runtime/src/main/resources/preanalyzed.vmlens");
  //  val fileOutputStream = new FileOutputStream("../test-vmlens-maven-plugin/target/vmlens-agent/preanalyzed.vmlens");
    val dataOutputStream = new DataOutputStream(fileOutputStream);
    val list = new PreAnalyzedFactory().create();
    new SerializePreAnalyzed().serialize(list, dataOutputStream);
    dataOutputStream.close();

  }

}
