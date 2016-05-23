package com.anz.UdpApi.transform.pojo;

import java.util.List;

public class Data {
	
	private String integrationNode;
	private String integrationServer;
	private String application;
	private Flow messageFlow;
	private List<Flow> subflows;
	
	
	public String getIntegrationNode() {
		return integrationNode;
	}
	public void setIntegrationNode(String integrationNode) {
		this.integrationNode = integrationNode;
	}
	public String getIntegrationServer() {
		return integrationServer;
	}
	public void setIntegrationServer(String integrationServer) {
		this.integrationServer = integrationServer;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public Flow getMessageFlow() {
		return messageFlow;
	}
	public void setMessageFlow(Flow messageFlow) {
		this.messageFlow = messageFlow;
	}
	public List<Flow> getSubflows() {
		return subflows;
	}
	public void setSubflows(List<Flow> subflows) {
		this.subflows = subflows;
	} 
	
	public String toString(){
		
		
		String data = "\n" + integrationNode + "\n" + integrationServer + "\n" + application + "\n" + messageFlow.getFlowName() + "\n";
		for(FlowUDP udp: messageFlow.getFlowUDPs()){
			
			data = data + "......... " + udp.getName() + " = " + udp.getValue() + "\n";	
					
		}
		
		return data;
	}

}
