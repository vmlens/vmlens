package com.vmlens.trace.agent.bootstrap;

/**
 * we log pro high level module/package
 * info -> input and outout of the module or if event based the main events
 * debug -> internals of the module or if event based all events
 * <p>
 *  ToDo Debug Config
 */
public abstract class Logger {

	private final LoggerConfig config;


	public Logger(LoggerConfig config) {
		this.config = config;
	}

	public boolean isInterleaveDebug() {
		return config.interleave.logDebug;
	}

	public void interleaveDebug(Class inClass, String message) {
		if (config.interleave.logDebug) {
			log("interleave", inClass, message);
		}
	}

	public void interleaveInfo(Class inClass, String message) {
		if (config.interleave.logInfo) {
            log("interleave", inClass, message);
		}
	}


    protected abstract void log(String module, Class inClass, String message);

}
