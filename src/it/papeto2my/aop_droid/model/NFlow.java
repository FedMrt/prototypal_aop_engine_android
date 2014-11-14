package it.papeto2my.aop_droid.model;

import it.papeto2my.aop_droid.model.NFlowable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NFlow {

	private NFlowable[] flowables;	
	private int flowStep;
	private int flowCount;
	
	public NFlow(int flowableNumber) {
		
		this.flowables = new NFlowable[flowableNumber];
	}
	
	public Object flow(Object target,Method targetMethod) throws Exception{
		
		Object returnedObj = null;
		
		if(flowStep == flowCount)
			returnedObj= targetMethod.invoke(target);
		else
			returnedObj = flowables[flowStep++].intercept(this,target,targetMethod);
		
		return returnedObj;
	}

	public void addFlowable(NFlowable flowable){
		flowables[flowCount++] = flowable;
	}

	public NFlowable[] getFlowables() {
		return flowables;
	}

	public void setFlowables(NFlowable[] flowables) {
		this.flowables = flowables;
	}
}
