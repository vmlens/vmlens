package com.vmlens.api.internal.reports

import com.samskivert.mustache.Mustache

import java.io.InputStreamReader


trait CreateTemplate {

  def create(template: String) = {
    Mustache.compiler.defaultValue("what").withLoader(new Mustache.TemplateLoader() {
      override def getTemplate(name: String) = {
        asFileReader(name)
      };
    }).compile(asFileReader(template));
  }


  private def asFileReader(name: String) = {
    val template = "/" + name;
    val stream = this.getClass.getResourceAsStream(template);
    if(stream==null) {
      throw new RuntimeException("Template not found " + template);
    }

    new InputStreamReader(stream)
  }

}
