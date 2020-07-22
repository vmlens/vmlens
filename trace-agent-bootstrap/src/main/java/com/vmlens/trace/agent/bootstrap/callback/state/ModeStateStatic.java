package com.vmlens.trace.agent.bootstrap.callback.state;

import gnu.trove.set.hash.THashSet;

public class ModeStateStatic {

	public long lastThreadId;
	public THashSet<Access4State> access4StateSet = new THashSet<Access4State>();
	public ModeStateStatic(long lastThreadId) {
		super();
		this.lastThreadId = lastThreadId;
	}
	
	
	
	
}
