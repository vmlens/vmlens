package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

public class CleanCallbackStatePerThreadRecovery extends Thread {

	public volatile boolean stopped = false;
	private final AgentController agentController;

	public CleanCallbackStatePerThreadRecovery(AgentController agentController) {
		super(CallbackStatePerThread.ANARSOFT_THREAD_NAME);
		this.setDaemon(true);
		this.agentController = agentController;

	}

	@Override
	public void run() {

		int iterations = 0;

		try {
			while (!stopped) {

				iterations++;

				if (iterations >= 10) {
					try {
						agentController.writeRunningState(WriteEvent2File.lastWrittenSlidingWindowId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					iterations = 0;
				}

				CallbackState.clearCallbackStatePerThreadRecovery();
				Thread.sleep(5);

			}

		} catch (InterruptedException e) {

		}

	}

}
