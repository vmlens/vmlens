package com.anarsoft.plugin.view;

import org.eclipse.jface.viewers.TreeViewer;

public class CallUpdateFromJava {

	
	public static void callUpdate( TreeViewer treeViewer , Object[] array)
	{
		treeViewer.update(array, null);
	}
	
	
}
