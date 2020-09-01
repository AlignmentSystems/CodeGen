package com.alignmentsystems.classgen.interfaces;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

public interface Action {
		void actionEvent(String methodName , String event);
		void actionEventError(String methodName , String event);
}
