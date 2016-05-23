package com.anz.UdpApi.compute;

import com.anz.UdpApi.transform.TransformUDPs;
import com.anz.common.compute.impl.CommonBlobTransformCompute;
import com.anz.common.transform.ITransformer;



/**
 * @author psamon
 *
 */
public class TransformUDPsCompute extends CommonBlobTransformCompute {

	/* (non-Javadoc)
	 * @see com.anz.common.compute.impl.CommonJsonJsonTransformCompute#getTransformer()
	 */
	@Override
	public ITransformer<String, String> getTransformer() {
		return new TransformUDPs();
	}


}
	

