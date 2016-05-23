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
	private static final int EMPTY = 0;
	
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
		logger.info("json = {}", json.toString());
		
		// Create proxies for broker, server and application
		BrokerProxy brokerProxy = BrokerProxy.getLocalInstance(json.getIntegrationNode());
		logger.info(brokerProxy.toString());
		ExecutionGroupProxy serverProxy = brokerProxy.getExecutionGroupByName(json.getIntegrationServer());	
		logger.info(serverProxy.toString());
		ApplicationProxy applicationProxy = serverProxy.getApplicationByName(json.getApplication());
		logger.info(applicationProxy.toString());
		
		
		// Create main message flow proxy
		FlowProxy flowProxy = applicationProxy.getMessageFlowByName(json.getMessageFlow().getFlowName());
		logger.info(flowProxy.toString());
		String[] udpNames =  flowProxy.getUserDefinedPropertyNames();
		
		for(String udp: udpNames){
			
			logger.info("UDPS: {}", udp);
			
		}
		
		if(json.getMessageFlow().getFlowUDPs() != null){
		
			// For each main flow UDP
			for(FlowUDP udp: json.getMessageFlow().getFlowUDPs()){
				
				
				
				logger.info("new value {} = {}", udp.getName(), udp.getValue());
				// Change UDP value
				flowProxy.setUserDefinedProperty(udp.getName(), udp.getValue());
				logger.info("new udp {} = {}", udp.getName(), flowProxy.getUserDefinedProperty(udp.getName()));
				
			}
			
		} else {
			
			logger.info("No message flow UPDs");
			
		}
		
		// If subflow list is not empty get data from subflows
		if(json.getSubflows() != null){
			
			// Create list of subflow proxies
			List<FlowProxy> subflowProxies = new ArrayList<FlowProxy>(json.getSubflows().size());
						
			// For each subflow
			for(Flow flow: json.getSubflows()){
							
				// Create subflow proxy
				FlowProxy newFlowProxy = applicationProxy.getSubFlowByName(flow.getFlowName());
							
				// If UDPs list is not empty get UDPs
				if(flow.getFlowUDPs() != null){
							
					// For each UDP
					for(FlowUDP udp: flow.getFlowUDPs()){
								
						// Change UDP value
						newFlowProxy.setUserDefinedProperty(udp.getName(), udp.getValue());
								
					}
							
					subflowProxies.add(newFlowProxy);
							
				} else {
					
					logger.info("No UDPs");
					
				}
				
			}
			
		} else {
			
			logger.info("No subflows");
		
		}
		
		// End User Code
		//-----------------------------------------------------------------------------------------
		String out = TransformUtils.toJSON(json);
		return out;
	}
}
