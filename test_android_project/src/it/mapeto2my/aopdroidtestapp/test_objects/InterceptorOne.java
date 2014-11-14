package it.mapeto2my.aopdroidtestapp.test_objects;

import it.papeto2my.aop_droid.model.NFlow;
import it.papeto2my.aop_droid.model.NFlowable;

import java.lang.reflect.Method;

import android.util.Log;


public class InterceptorOne implements NFlowable{

	@Override
	public Object intercept(NFlow flow, Object target, Method targetMethod) throws Exception{

		Object retVal = null;
		
		Log.d("AOPDroid","*** InterceptorOne *** PRE ***");
	
		retVal = flow.flow(target,targetMethod);
		
		Log.d("AOPDroid","*** InterceptorOne *** POST ***");
	
		return retVal;
	}

}
