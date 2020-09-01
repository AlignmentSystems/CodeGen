package com.alignmentsystems.classgen;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import com.alignmentsystems.classgen.generator.GenerateFramework;

/**
 * @author John.Greenan
 *
 */
public class Worker {
	public static void main(String[] args) {
		Thread myWorker = new Thread(new GenerateFramework(args)); 
		myWorker.start();
	}
}
