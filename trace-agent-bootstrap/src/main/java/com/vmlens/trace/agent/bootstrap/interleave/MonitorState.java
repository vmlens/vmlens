package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Arrays;

public class MonitorState {

	private int[] monitorArray;
	private int[] exclusiveLockArray;
	private int[] sharedLockArray;
	
	public MonitorState(int[] monitorArray, int[] exclusiveLockArray, int[] sharedLockArray) {
		super();
		this.monitorArray = monitorArray;
		this.exclusiveLockArray = exclusiveLockArray;
		this.sharedLockArray = sharedLockArray;
	}

	public boolean contains(MonitorState other) {
		
		for( int i : monitorArray )
		{
			for(int j : other.monitorArray )
			{
				if( i == j  )
				{
					return true;
				}
			}
		}
		
		
		for( int i : exclusiveLockArray )
		{
			for(int j : other.exclusiveLockArray )
			{
				if( i == j  )
				{
					return true;
				}
			}
			
			for(int j : other.sharedLockArray )
			{
				if( i == j  )
				{
					return true;
				}
			}
			
			
			
		}
		
		
		for( int i : sharedLockArray )
		{
			for(int j : other.exclusiveLockArray )
			{
				if( i == j  )
				{
					return true;
				}
			}
		}
		
		
		
		return false;
	}

	@Override
	public String toString() {
		return "MonitorState [monitorArray=" + Arrays.toString(monitorArray) + ", exclusiveLockArray="
				+ Arrays.toString(exclusiveLockArray) + ", sharedLockArray=" + Arrays.toString(sharedLockArray) + "]";
	}
	
	
}
