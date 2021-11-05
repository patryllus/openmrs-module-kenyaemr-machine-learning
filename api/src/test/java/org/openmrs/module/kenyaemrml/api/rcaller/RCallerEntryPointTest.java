package org.openmrs.module.kenyaemrml.api.rcaller;

import junit.framework.TestCase;
import org.junit.Test;

public class RCallerEntryPointTest extends TestCase {
	
	@Test
	public void shouldTestTheEntryPoint() {
		RCallerEntryPoint ePoint = new RCallerEntryPoint();
		ePoint.rcaller();
		
	}
	
}
