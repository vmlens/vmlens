package com.anarsoft.plugin.sync.bug


import ch.qos.logback.classic.spi.Configurator
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.spi.ContextAwareBase;
import org.slf4j.Logger
import ch.qos.logback.classic.Level 



class LogbackConfigurator extends ContextAwareBase with Configurator  {
  
  def configure( loggerContext : LoggerContext)
  {
    val rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
    rootLogger.setLevel(Level.ERROR);
  }
  
  
}