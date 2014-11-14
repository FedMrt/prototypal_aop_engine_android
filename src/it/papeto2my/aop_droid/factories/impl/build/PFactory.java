package it.papeto2my.aop_droid.factories.impl.build;

import it.papeto2my.aop_droid.factories.Factory;
import it.papeto2my.aop_droid.model.Flowable;
import it.papeto2my.aop_droid.model.NFlow;
import it.papeto2my.aop_droid.model.NFlowable;
import it.papeto2my.aop_droid.utils.Utilities;
import it.papeto2my.aop_droid.utils.Utilities.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PFactory implements Factory {

	private static final String CONF_FILE_PATH = "test2/aop_conf.json";
	
	public static PFactory singleInstance;

	private Map<String,Object> targets = new HashMap<String,Object>();
	
	
	public static PFactory getSingleInstance() throws Exception{
	
		if(singleInstance == null)
			singleInstance = new PFactory();
		
		return singleInstance;
	}

	@Override
	public Object getTarget(String key) throws Exception{
		return targets.get(key);
	}
	
	@SuppressWarnings("rawtypes")
	private PFactory() throws Exception{
		
		Utilities.Target[] targetsInfos = 
				Utilities.getTargetsInfos(this.getClass()
												.getClassLoader()
													.getResourceAsStream(CONF_FILE_PATH));
		
		for (Target target : targetsInfos) {
			
			Class targetClass = Class.forName(target.getTargetClassName());
			Class targetInterfaceClass = Class.forName(target.getTargetClassInterfaceName());
			String[] interceptorClassNames = target.getInterceptorsClassName();
			Object targetClassInstance = targetClass.newInstance();
			NFlow nFlow = new NFlow(interceptorClassNames.length);
			
			for(int i=0; i<interceptorClassNames.length; i++){
				Class interceptorClass = Class.forName(interceptorClassNames[i]);
				nFlow.addFlowable((NFlowable) interceptorClass.newInstance()); 
			}
			
			Object proxiedInstance = Proxy.newProxyInstance(targetClassInstance.getClass().getClassLoader(), 
															new Class[]{targetInterfaceClass}, 
																new Handler(targetClassInstance,nFlow));
			
			targets.put(target.getTargetIdentifier(), proxiedInstance);
		}
		
	}

	private class Handler implements InvocationHandler{
		
		/* Avanti con l'handler per invocazione interceptors */
		
		private Object targetObject;
		private NFlow nFlow;
		
		public Handler(Object targetObject, NFlow nFlow) {
			// TODO Auto-generated constructor stub
			this.targetObject = targetObject;
			this.nFlow = nFlow;
		}
		
		@Override
		public Object invoke(Object proxy, 
								Method targetMethod, 
									Object[] targetMethodParams)
				throws Throwable {
			
			return nFlow.flow(targetObject, targetMethod);
			
		}		
	}
}