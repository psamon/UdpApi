/**
 * 
 */
package com.anz.UdpApi.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;



import org.apache.logging.log4j.Logger;

import com.anz.UdpApi.transform.pojo.Data;
import com.anz.UdpApi.transform.pojo.Flow;
import com.anz.UdpApi.transform.pojo.FlowUDP;
import com.anz.UdpApi.transform.pojo.NumbersInput;


import com.anz.common.cache.impl.CacheHandlerFactory;
import com.anz.common.compute.ComputeInfo;
import com.anz.common.dataaccess.models.iib.Operation;
import com.anz.common.domain.OperationDomain;
import com.anz.common.transform.ITransformer;
import com.anz.common.transform.TransformUtils;
import com.ibm.broker.config.proxy.ApplicationProxy;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.FlowProxy;


/**
 * @author sanketsw
 * 
 */
public class TransformUDPs implements ITransformer<String, String> {

	private static final Logger logger = LogManager.getLogger();
	
	/* (non-Javadoc)
	 * @see com.anz.common.transform.IJsonJsonTransformer#execute(java.lang.String)
	 */
	public String execute(String inputJson, Logger appLogger, ComputeInfo metadata) throws Exception {
		Data json = (Data) TransformUtils.fromJSON(inputJson,
				Data.class);
		logger.info("Inside PreTransform");
		//throw new Exception("Error in request transform- user created");
		
		//-----------------------------------------------------------------------------------------
		// User Code Below
		
		// TODO handle naming errors
		
		// Create proxies for broker, server and application
		BrokerProxy brokerProxy = BrokerProxy.getLocalInstance(json.getIntegrationNode());
		ExecutionGroupProxy serverProxy = brokerProxy.getExecutionGroupByName(json.getIntegrationServer());		
		ApplicationProxy applicationProxy = serverProxy.getApplicationByName(json.getApplication());
		
		
		// Create main message flow proxy
		FlowProxy flowProxy = applicationProxy.getMessageFlowByName(json.getMessageFlow().getFlowName());
		
		// For each main flow UDP
		for(FlowUDP udp: json.getMessageFlow().getFlowUDPs()){
			
			// Change UDP value
			flowProxy.setUserDefinedProperty(udp.getName(), udp.getValue());
			
		}
		
		// Create list of subflow proxies
		List<FlowProxy> subflowProxies = new ArrayList<FlowProxy>(json.getSubflows().size());
		
		// For each subflow
		for(Flow flow: json.getSubflows()){
			
			// Create subflow proxy
			FlowProxy newFlowProxy = applicationProxy.getSubFlowByName(flow.getFlowName());
			
			// For each UDP
			for(FlowUDP udp: flow.getFlowUDPs()){
				
				// Change UDP value
				newFlowProxy.setUserDefinedProperty(udp.getName(), udp.getValue());
				
			}
			
			subflowProxies.add(newFlowProxy);
			
		}
		
		

		
		
		// End User Code
		//-----------------------------------------------------------------------------------------
		String out = TransformUtils.toJSON(json);
		return out;
	}
}
