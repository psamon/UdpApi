package com.anz.UdpApi.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.anz.UdpApi.transform.pojo.NumbersInput;
import com.anz.common.transform.TransformUtils;

public class PostTransformBLSampleTest {

	@Test
	public void testExexcute() throws Exception {
		String inputJson = "{ \"left\": 5, \"right\": 6}";
		String out = new PostTransformBLSample().execute(inputJson, null, null);
		assertNotNull(out);
		NumbersInput n = TransformUtils.fromJSON(out, NumbersInput.class);
		assertNotNull(n);
		assertEquals(106, n.getRight());
	}
	
	@Test
	public void testExexcuteFailure() {
		String inputJson = "";
		@SuppressWarnings("unused")
		String out;
		Exception exception = null;
		try {
			out = new PostTransformBLSample().execute(inputJson, null, null);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
	}

}
