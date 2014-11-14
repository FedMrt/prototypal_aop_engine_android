package it.papeto2my.aop_droid.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utilities {

	
	public static Target[] getTargetsInfos(InputStream confFileInputStream) throws Exception{
		
		
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new InputStreamReader(confFileInputStream));
		JSONObject rootObject = (JSONObject) obj; /* JSONObject associato alla root JSON */
		
		JSONArray targets = (JSONArray) rootObject.get("targets"); /* JSONArray targets*/
		
		Iterator<JSONObject> targetsIterator = targets.iterator();
		
		
		Target[] listaInformazioniTargets = new Target[targets.size()];
	
		int targetIterationCount = 0;
		while (targetsIterator.hasNext()) { /* Iteriamo sul JSONArray targets */
			
			JSONObject targetDetail = targetsIterator.next();
				
		
			Target target = new Target();
			target.setTargetIdentifier((String) targetDetail.get("target_name"));
			target.setTargetClassName((String) targetDetail.get("target_class"));
			target.setTargetClassInterfaceName((String) targetDetail.get("target_interface"));
			
			
			
			JSONArray flowComponents = (JSONArray) targetDetail.get("flow_components"); /* JSONArray flowComponents*/
			Iterator<String> flowComponentsIterator = flowComponents.iterator();
				
			String[] listaflowComponents = new String[flowComponents.size()];
			int flowComponentsIterationCount = 0;
			while (flowComponentsIterator.hasNext()) { /* Iteriamo sul JSONArray targets */
				
				String flowComponent = flowComponentsIterator.next();
				listaflowComponents[flowComponentsIterationCount++] = flowComponent;
				
			}
			
			target.setInterceptorsClassName(listaflowComponents);
			
			listaInformazioniTargets[targetIterationCount++] = target;	
		}

		return listaInformazioniTargets;
	}
	
	
	public static class Target{
		
		private String targetIdentifier;
		private String targetClassName;
		private String targetClassInterfaceName;
		private String[] interceptorsClassName;
		
		public Target() {
			// TODO Auto-generated constructor stub
		}
		
		public Target(String targetIdentifier,String targetClassName, String targetClassInterfaceName,
				String[] interceptorsClassName) {
			
			this.targetIdentifier = targetIdentifier;
			this.targetClassName = targetClassName;
			this.targetClassInterfaceName = targetClassInterfaceName;
			this.interceptorsClassName = interceptorsClassName;
		}
		
		public String getTargetClassName() {
			return targetClassName;
		}
		public void setTargetClassName(String targetClassName) {
			this.targetClassName = targetClassName;
		}
		public String[] getInterceptorsClassName() {
			return interceptorsClassName;
		}
		public void setInterceptorsClassName(String[] interceptorsClassName) {
			this.interceptorsClassName = interceptorsClassName;
		}
		public String getTargetClassInterfaceName() {
			return targetClassInterfaceName;
		}
		public void setTargetClassInterfaceName(String targetClassInterfaceName) {
			this.targetClassInterfaceName = targetClassInterfaceName;
		}

		public String getTargetIdentifier() {
			return targetIdentifier;
		}

		public void setTargetIdentifier(String targetIdentifier) {
			this.targetIdentifier = targetIdentifier;
		}	
	
	}
}
