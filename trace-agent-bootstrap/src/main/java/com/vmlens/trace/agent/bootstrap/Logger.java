package com.vmlens.trace.agent.bootstrap;


public abstract class Logger {

	private final boolean orderEnabled;

	public Logger(boolean orderEnabled) {
		this.orderEnabled = orderEnabled;
	}

	public boolean isOrderEnabled() {
		return orderEnabled;
	}

	public void order(String order) {
		if(orderEnabled) {
			logOrder(order);
		}
	}

	/* Fixme
	performance
	parallizeRun

	 */

	protected abstract void logOrder(String order);


	
}
