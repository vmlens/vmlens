package com.vmlens.trace.agent.bootstrap.callback;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionFilter {

	
	
	private static int getGeneratedMethodCount(Method[] methodArray)
	{
		int count = 0;
		
		
		for( Method method : methodArray)
		{
			if(  method.getName().startsWith("_pAnarsoft_"))
			{
				count++;
			}
		}
		
		
		return count;
		
		
		
	}
	
	
	public static Method[] getFilteredDeclaredMethods(Class cl)
	{
		Method[] methodArray = cl.getDeclaredMethods();
		
		int count = getGeneratedMethodCount(methodArray);
		
		if(count == 0)
		{
			return methodArray;
		}
		
		
		Method[] newArray = new Method[ methodArray.length - count   ];
		
		int newIndex = 0;
		
		for( Method method : methodArray)
		{
			if(  ! method.getName().startsWith("_pAnarsoft_"))
			{
				
				newArray[newIndex] = method;
				newIndex++;
			}
		}
		
		return newArray;
		
		
		
		
	}
	
	private static int getGeneratedFieldCount(Field[] methodArray)
	{
		int count = 0;
		
		
		for( Field method : methodArray)
		{
			if(  method.getName().startsWith("_pAnarsoft_"))
			{
				count++;
			}
		}
		
		
		return count;
		
		
		
	}
	
	
	
	public static Field[] getFilteredDeclaredFields(Class cl)
	{
		Field[] methodArray = cl.getDeclaredFields();
		
		int count = getGeneratedFieldCount(methodArray);
		
		if(count == 0)
		{
			return methodArray;
		}
		
		
		Field[] newArray = new Field[ methodArray.length - count   ];
		
		int newIndex = 0;
		
		for( Field method : methodArray)
		{
			if(  ! method.getName().startsWith("_pAnarsoft_"))
			{
				
				newArray[newIndex] = method;
				newIndex++;
			}
		}
		
		return newArray;
		
	}
	
	
	
	public static Method[] getFilteredMethods(Class cl)
	{
		Method[] methodArray = cl.getMethods();
		
		int count = getGeneratedMethodCount(methodArray);
		
		if(count == 0)
		{
			return methodArray;
		}
		
		
		Method[] newArray = new Method[ methodArray.length - count   ];
		
		int newIndex = 0;
		
		for( Method method : methodArray)
		{
			if(  ! method.getName().startsWith("_pAnarsoft_"))
			{
				
				newArray[newIndex] = method;
				newIndex++;
			}
		}
		
		return newArray;
		
		
		
		
	}
	
	
	
	
	
	
	
}
