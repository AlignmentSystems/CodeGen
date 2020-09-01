package com.alignmentsystems.classgen.enumerations;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st April 2020
 * Copyright       : Alignment Systems Ltd 2018-2019-2020
 * 
 *****************************************************************************/

public enum ImplementationLanguage {
	Java(1),
	Python(2),
	Cpp(3),
	CSharp(4),
	q(5);

	private final int FieldNumber;

	ImplementationLanguage(int FieldNumber) {
		this.FieldNumber= FieldNumber;
	}

	/**
	 * 
	 * @author <a href="mailto:john.greenan@alignment-systems.com">John Greenan</a>
	 * @return
	 */
	public int getFieldNumber() {
		return this.FieldNumber;
	}

}
