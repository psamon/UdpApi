package com.anz.UdpApi.transform;

import static org.junit.Assert.*;

import org.junit.Test;

import com.anz.UdpApi.transform.pojo.NumbersInput;
import com.anz.common.transform.TransformUtils;

public class PreTransformBLSampleTest {

	@Test
	public void testExexcute() throws Exception {
		String inputJson = "{ \"left\": 5, \"right\": 6}";
		String out = new PreTransformBLSample().execute(inputJson, null, null);
		assertNotNull(out);
		NumbersInput n = TransformUtils.fromJSON(out, NumbersInput.class);
		assertNotNull(n);
		assertEquals(105, n.getLeft());
	}
	
	@Test
	public void testExexcuteFailure() {
		String inputJson = "";
		@SuppressWarnings("unused")
		String out;
		Exception exception = null;
		try {
			out = new PreTransformBLSample().execute(inputJson, null, null);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
	}

}
