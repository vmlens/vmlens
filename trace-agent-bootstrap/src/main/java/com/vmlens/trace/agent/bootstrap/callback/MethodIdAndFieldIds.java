package com.vmlens.trace.agent.bootstrap.callback;

import gnu.trove.list.linked.TIntLinkedList;

public class MethodIdAndFieldIds {
	
	
	public int methodId;
	public TIntLinkedList readIds = new TIntLinkedList();
	public TIntLinkedList writeIds = new TIntLinkedList();

}
