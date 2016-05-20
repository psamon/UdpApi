/**
 * 
 */
package com.anz.UdpApi.compute;

import com.anz.common.compute.TransformType;
import com.anz.common.compute.impl.CommonErrorTransformCompute;

/**
 * @author SANKETSW
 *
 */
public class FailureTransformCompute extends CommonErrorTransformCompute {

	@Override
	public TransformType getTransformationType() {
		// TODO Auto-generated method stub
		return TransformType.HTTP_MQ;
	}

}
