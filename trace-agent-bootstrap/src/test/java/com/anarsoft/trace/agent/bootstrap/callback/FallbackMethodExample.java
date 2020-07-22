package com.anarsoft.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.FieldAccessCallback;

public class FallbackMethodExample {

	

	public void _pAnarsoft_field_access(int fieldId, int methodId , int callbackId)
	{
		FieldAccessCallback.field_access(this , fieldId, methodId, callbackId);
	}
	
}
