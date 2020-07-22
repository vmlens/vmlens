package com.vmlens.util;


import static org.junit.Assert.*;
import org.junit.Test;

import com.anarsoft.trace.agent.runtime.util.AntPatternMatcher;

public class AntPatternMatcherTest {

	@Test
	public void test() {
		AntPatternMatcher antPatternMatcher = new AntPatternMatcher();
		
	      assertTrue( antPatternMatcher.match("org.junit.**" , "org.junit.Testrunner.test"  ) );
	      assertTrue( antPatternMatcher.match("com.vmlens.test.regression.TestAllTypes.*" , "com.vmlens.test.regression.TestAllTypes.testArrayAccess"  ) );
	      assertTrue( antPatternMatcher.match("com.vmlens.test.regression.TestWithInnerClass*.**" , "com.vmlens.test.regression.TestWithInnerClass$RaceOne.i"  ) );
	      
	     
	      
	      
	}
	
	@Test
	public void testSimpleStringMatches()
	{
		  AntPatternMatcher antPatternMatcher = new AntPatternMatcher();
		  assertTrue( antPatternMatcher.match("org.junit" , "org.junit"  ) );
	}
	

}
