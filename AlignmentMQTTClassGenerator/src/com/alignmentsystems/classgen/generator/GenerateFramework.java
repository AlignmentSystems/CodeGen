package com.alignmentsystems.classgen.generator;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alignmentsystems.classgen.enumerations.ImplementationLanguage;
import com.alignmentsystems.classgen.exceptions.FieldListException;
import com.alignmentsystems.classgen.exceptions.MessageListException;
import com.alignmentsystems.classgen.interfaces.Action;

public class GenerateFramework implements Runnable , Action{
	private final Logger log = LoggerFactory.getLogger(GenerateFramework.class);
	private String[] privateArgs = null;
	private Boolean logIt = Boolean.FALSE;
	private final ImplementationLanguage implementationLanguage = ImplementationLanguage.Java;
	private final static String packageNameForMessagesT = "com.alignmentsystems.sbe";
	private final static String templateNameForTransactions = "resources/vtemplates/class.vm";



	/**
	 * 
	 * @param args
	 */
	public GenerateFramework(String[] args) {
		this.privateArgs = args;

	}

	/**
	 * 
	 * @param writer
	 * @param modelName
	 * @return
	 */
	protected Boolean writeToFile(StringWriter writer, String modelName) {
		Boolean retVal = Boolean.FALSE;

		try {
			PrintWriter out = new PrintWriter(modelName + ".java");
			out.println(writer.toString());
			out.close();
		} catch (FileNotFoundException e) {
			log.error(e.toString(),e);
		}
		return retVal;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		final String methodName = this.getClass().getSimpleName() + "<run>";	

		final Boolean doWeValidateSchemeVersusXSD = Boolean.FALSE;
		final String pathToApplicationMessagesSchema = "resources/schema/Examples.xml";
		final String pathToApplicationMessagesXSD = "resources/xsd/sbe.xsd";
		final XMLFunctions functions = new XMLFunctions();
		functions.addListener(this);

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();


		if (doWeValidateSchemeVersusXSD) {
			Boolean isValid = functions.validateXMLSchema(pathToApplicationMessagesXSD, pathToApplicationMessagesSchema);
		}


		List<String> listOfMessageNames = null;
		try {
			listOfMessageNames = functions.getMessageListFromXML(pathToApplicationMessagesSchema);
		} catch (MessageListException e) {
			this.actionEventError(methodName, e.toString());
		}

		//		listOfMessageNames.forEach(entry->{
		//			log.info(entry.toString());
		//		});
		//

		listOfMessageNames.forEach(entry->{

			Template t = null;
			
			try {
				t = velocityEngine.getTemplate(templateNameForTransactions);
			}catch(ResourceNotFoundException | ParseErrorException e) {
				this.actionEventError(methodName, e.toString());
			} 

			
			
			String modelName;
			String author = null;
			String creationDate = null;
			String packageNameForTransactions = null;

			modelName = entry.toString();


			List<Field> properties = null;
			try {
				properties = functions.getFieldListFromXMLForThisMessage(modelName, pathToApplicationMessagesSchema);
			} catch (FieldListException e) {
				this.actionEventError(methodName, e.toString());
			}					

			for (Field fld : properties ) {
				log.info(fld.toString());
			}



			author = GeneratorFunctions.getOneLineComment("Author : John Greenan", implementationLanguage);
			creationDate = GeneratorFunctions.getOneLineComment("Creation Date : " + GeneratorFunctions.getCreationDate(),implementationLanguage);
			packageNameForTransactions = GeneratorFunctions.getDateValue(packageNameForMessagesT);

			VelocityContext context = new VelocityContext();

			if(Objects.isNull(packageNameForTransactions)){ 
				//do nothing...
			}else {
				context.put("packagename", packageNameForTransactions);
			}

			if(Objects.isNull(author)){ 
				//do nothing...
			}else {
				context.put("author", author);
			}


			if(Objects.isNull(modelName)){ 
				//do nothing...
			}else {
				context.put("className", modelName);

			}

			if(Objects.isNull(properties)){ 
				//do nothing...
			}else {
				context.put("properties", properties);
			}



			if(Objects.isNull(creationDate)){ 
				//do nothing...
			}else {
				context.put("creationDate", creationDate);
			}



			StringWriter writer = new StringWriter();
			t.merge( context, writer );


			System.out.println(writer.toString());	

			writeToFile(writer, modelName);

			//break;
		});
	}

	@Override
	public void actionEvent(String methodName, String event) {
		this.log.info(methodName + "=>" + event);

	}

	@Override
	public void actionEventError(String methodName, String event) {
		this.log.error(methodName + "=>" + event);

	}
}