package it.papeto2my.aop_droid.model;

import it.papeto2my.aop_droid.model.NFlow;

import java.lang.reflect.Method;

public interface NFlowable {

	Object intercept(NFlow flow,Object target, Method targetMethod) throws Exception;
	
}
