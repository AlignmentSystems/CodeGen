package com.alignmentsystems.classgen.mapper;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import java.util.HashMap;

public final  class MapDataTypes {

	private static HashMap<String, Mapper> fromXmlToJavaType = new HashMap<>();
	private static HashMap<String, Mapper> fromXmlToqType = new HashMap<>();

	
	/**
	 * 
	 */
	public MapDataTypes() {		
		fromXmlToJavaType.put("idString", new Mapper(String.class.getSimpleName(), null,null));
		fromXmlToJavaType.put("DATA", new Mapper(String.class.getSimpleName(), null,null));
			
		
	}
	
	/**
	 * 
	 * @param xmlTypeName
	 * @return
	 */
	public static String getJavaTypeNameForXMLTypeName(String xmlTypeName) {
		if (fromXmlToJavaType.containsKey(xmlTypeName)) {
			return fromXmlToJavaType.get(xmlTypeName).getJavaFieldName();
		}else {
			return xmlTypeName;
			//return null;
		}
	}
	
	/**
	 * 
	 * @param xmlTypeName
	 * @return
	 */
	public static String getQTypeNameForXMLTypeName(String xmlTypeName) {
		if (fromXmlToqType.containsKey(xmlTypeName)) {
			return fromXmlToqType.get(xmlTypeName).getJavaFieldName();
		}else {
			return xmlTypeName;
			//return null;
		}
	}
	
	
	
}