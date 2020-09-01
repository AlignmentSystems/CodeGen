package com.alignmentsystems.classgen.exceptions;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import javax.xml.parsers.ParserConfigurationException;

public class MessageListException extends Exception{
	private static final long serialVersionUID = 3L;

	/**
	 * @author john.greenan
	 * @param s
	 */
	public MessageListException(String s) {
		super(s);
	}

	public MessageListException(ParserConfigurationException e) {
		super(e);
	}

	public MessageListException(Exception e) {
		super(e);
	}
}
