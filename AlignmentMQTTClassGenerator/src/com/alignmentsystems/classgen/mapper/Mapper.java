package com.alignmentsystems.classgen.mapper;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

public class Mapper {

	private String javaFieldName;
	private String toStringPresentationPrefix;
	private String toStringPresentationSuffix;
		
	
	/**
	 * 
	 * @param javaFieldName
	 * @param toStringPresentationPrefix
	 * @param toStringPresentationSuffix
	 */
	public Mapper(String javaFieldName, String toStringPresentationPrefix, String toStringPresentationSuffix) {
		this.javaFieldName = javaFieldName;
		this.toStringPresentationPrefix = toStringPresentationPrefix;
		this.toStringPresentationSuffix = toStringPresentationSuffix;
	} 

	/**
	 * 
	 * @return
	 */
	public String getJavaFieldName() {
		return this.javaFieldName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getToString() {
		return "+ \"," + this.javaFieldName + "=\" + " + toStringPresentationPrefix + this.javaFieldName + this.toStringPresentationSuffix;
	}
}
