package it.papeto2my.aop_droid.model;

import it.papeto2my.aop_droid.model.Flow;

import java.lang.reflect.Method;

public interface Flowable {

	Object intercept(Flow flow,Object target, Method targetMethod) throws Exception;
	
}
