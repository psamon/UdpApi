package com.anz.UdpApi.transform.pojo;

import java.util.List;

public class Flow {
	
	private String flowName;
	//private boolean subflow;
	private List<FlowUDP> flowUDPs;
	
	
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	/*public boolean isSubflow() {
		return subflow;
	}
	public void setSubflow(boolean subflow) {
		this.subflow = subflow;
	}*/
	public List<FlowUDP> getFlowUDPs() {
		return flowUDPs;
	}
	public void setFlowUDPs(List<FlowUDP> flowUDPs) {
		this.flowUDPs = flowUDPs;
	}
	
	

}
