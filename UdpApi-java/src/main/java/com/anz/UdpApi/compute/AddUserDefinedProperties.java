
/**
 * 
 */
package com.anz.UdpApi.compute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anz.common.compute.TransformType;
import com.anz.common.compute.impl.CommonJavaCompute;
import com.anz.common.compute.impl.ComputeUtils;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessageAssembly;

/**
 * @author sanketsw & psamon
 *
 */
public class AddUserDefinedProperties extends CommonJavaCompute {

	private static final Logger logger = LogManager.getLogger();


	@Override
	public void execute(MbMessageAssembly inAssembly,
			MbMessageAssembly outAssembly) throws Exception {

		// Set ReplyToQ MQMD field to the UDP REPLY_QUEUE
		MbElement replyToQ = ComputeUtils.setElementInTree(getUserDefinedAttribute("REPLY_QUEUE"), outAssembly.getMessage(), "MQMD","ReplyToQ");
		logger.info("{} = {}", replyToQ.getName(), replyToQ.getValueAsString());
		
		// Set ReplyToQMgr MQMD field to the UDP REPLY_QUEUE_MGR
		MbElement replyToQMgr = ComputeUtils.setElementInTree(getUserDefinedAttribute("REPLY_QUEUE_MGR"), outAssembly.getMessage(), "MQMD","ReplyToQMgr");
		logger.info("{} = {}", replyToQMgr.getName(), replyToQMgr.getValueAsString());			

		// Set the Local Environment MQ output queue parameter to the UDP PROVIDER_QUEUE
		MbElement providerQ = ComputeUtils.setElementInTree(getUserDefinedAttribute("PROVIDER_QUEUE"), outAssembly.getLocalEnvironment(),"Destination", "MQ","DestinationData","queueName");
		logger.info("{} = {}", providerQ.getName(), providerQ.getValueAsString());	
		
		// Set the Local Environment MQ output queue manager parameter to the UDP PROVIDER_QUEUE_MGR
		MbElement providerQMgr = ComputeUtils.setElementInTree(getUserDefinedAttribute("PROVIDER_QUEUE_MGR"), outAssembly.getLocalEnvironment(),"Destination", "MQ","DestinationData","queueManagerName");
		logger.info("{} = {}", providerQMgr.getName(), providerQMgr.getValueAsString());
		
		
	}
	 

	@Override
	public TransformType getTransformationType() {
		// TODO Auto-generated method stub
		return TransformType.HTTP_MQ;
	}
}
