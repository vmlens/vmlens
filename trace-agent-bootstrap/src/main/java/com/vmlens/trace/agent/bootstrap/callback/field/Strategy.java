package com.vmlens.trace.agent.bootstrap.callback.field;

public interface Strategy {

	void field_access_default(Object orig,  int fieldId, int methodId);
	void field_access_static(int fieldId, int methodId);
	void field_access_generated(Object orig, long offset, int fieldId, int methodId);
}
