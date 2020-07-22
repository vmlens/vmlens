package com.anarsoft.trace.agent.runtime.transformer;

public enum MethodLockTypeEnum implements MethodLockType {

		NONE {

			@Override
			public String methodName() {
			
				return null;
			}

			@Override
			public boolean callCallbacck() {
			
				return false;
			}   } 
		
		, EXCLUSIVE_LOCK_ENTER 
		{
		@Override
		public String methodName() {
		
			return "exclusiveLockEnter";
		}

		@Override
		public boolean callCallbacck() {
		
			return true;
		}   } 
		
		, EXCLUSIVE_LOCK_EXIT 
		{
			@Override
			public String methodName() {
			
				return "exclusiveLockExit";
			}

			@Override
			public boolean callCallbacck() {
			
				return true;
			}   } 
		
		
		, SHARED_LOCK_ENTER 
		{
			@Override
			public String methodName() {
			
				return "sharedLockEnter";
			}

			@Override
			public boolean callCallbacck() {
			
				return true;
			}   } 
		, SHARED_LOCK_EXIT
		{
			@Override
			public String methodName() {
			
				return "sharedLockExit";
			}

			@Override
			public boolean callCallbacck() {
			
				return true;
			}   } 
}
