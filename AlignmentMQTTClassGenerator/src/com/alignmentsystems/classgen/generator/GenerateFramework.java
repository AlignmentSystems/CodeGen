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
import org.apache.velocity.exception.MethodInvocationException;
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
	 * @param implementationLanguage
	 * @return
	 */
	protected Boolean writeToFile(StringWriter writer , String modelName , ImplementationLanguage implementationLanguage) {
		final String methodName = this.getClass().getSimpleName() + "::writeToFile";	
		final String extensionForJava = ".java";
		final String extensionForQ = ".q";
		String extensionToUse = null; 

		if (implementationLanguage==ImplementationLanguage .Java) {
			extensionToUse = extensionForJava;
		}else if (implementationLanguage==ImplementationLanguage.q) {
			extensionToUse = extensionForQ;
		}


		Boolean retVal = Boolean.FALSE;

		try {
			PrintWriter out = new PrintWriter(modelName + extensionToUse);
			out.println(writer.toString());
			out.close();
			retVal = Boolean.TRUE;
		} catch (FileNotFoundException e) {
			this.actionEventError(methodName, e.toString());
		}
		return retVal;
	}



	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		final String methodName = this.getClass().getSimpleName() + "<run>";	

		final Boolean doWeValidateSchemeVersusXSD = Boolean.TRUE;
		final String pathToApplicationMessagesSchema = "resources/schema/ExampleSensor.xml";

		//final String pathToApplicationMessagesSchema = "resources/schema/Examples.xml";
		final String pathToApplicationMessagesXSD = "resources/xsd/sbe.xsd";
		final String templateNameForJava = "resources/vtemplates/class.java.vm";
		final String templateNameForQ = "resources/vtemplates/class.kdb.vm";

		final XMLFunctions functions = new XMLFunctions();



		functions.addListener(this);

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();


		if (doWeValidateSchemeVersusXSD) {
			Boolean isValid = functions.validateXMLSchema(pathToApplicationMessagesXSD, pathToApplicationMessagesSchema);
			if(isValid) {
				this.actionEvent(methodName, pathToApplicationMessagesSchema + " validated against " + pathToApplicationMessagesXSD);
			}else {
				this.actionEventError(methodName, pathToApplicationMessagesSchema + " not validated against " + pathToApplicationMessagesXSD);
			}
		}

		try {
			final List<String> listOfMessageNames = functions.getMessageListFromXML(pathToApplicationMessagesSchema);
			listOfMessageNames.forEach(entry->{
				ImplementationLanguage writeLanguage; 
				writeLanguage = ImplementationLanguage.Java;
				Boolean executeJava = executeForImplementation(
						velocityEngine
						, functions 
						, entry.toString()						
						, templateNameForJava
						, pathToApplicationMessagesSchema 
						, writeLanguage
						);
				if(executeJava) {
					this.actionEvent(methodName , entry.toString() + " [" + writeLanguage.name() + "] written to file..."); 
				}else {
					this.actionEventError(methodName , entry.toString() + " not written to file...");
				}

				writeLanguage = ImplementationLanguage.q;
				Boolean executeQ = executeForImplementation(
						velocityEngine
						, functions 
						, entry.toString()						
						, templateNameForQ
						, pathToApplicationMessagesSchema 
						, writeLanguage
						);
				if(executeQ) {
					this.actionEvent(methodName , entry.toString() + " [" + writeLanguage.name() + "] written to file..."); 
				}else {
					this.actionEventError(methodName , entry.toString() + " not written to file...");
				}
			});
		} catch (MessageListException e) {
			this.actionEventError(methodName, e.toString());
		}
	}


	/**
	 * 
	 * @param velocityEngine VelocityEngine
	 * @param modelName String
	 * @param templateName String
	 * @param implementationLanguage ImplementationLanguage
	 * @return Boolean 
	 */
	private Boolean executeForImplementation(VelocityEngine velocityEngine , XMLFunctions functions ,String modelName , String templateName , String pathToSchema , ImplementationLanguage implementationLanguage) {
		final String methodName = this.getClass().getSimpleName() + "::executeForImplementation";	

		final String packageNameForMessagesT = "com.alignmentsystems.sbe";
		final String author = GeneratorFunctions.getOneLineComment("Author : John Greenan" , implementationLanguage);
		final String creationDate = GeneratorFunctions.getOneLineComment("Creation Date : " + GeneratorFunctions.getCreationDate(),implementationLanguage);
		final String packageNameForTransactions = GeneratorFunctions.getDateValue(packageNameForMessagesT);


		Boolean retVal = Boolean.FALSE;

		Template t = null;

		try {
			t = velocityEngine.getTemplate(templateName);
		}catch(ResourceNotFoundException | ParseErrorException e) {
			this.actionEventError(methodName, e.toString());
		} 


		List<Field> properties = null;
		try {
			properties = functions.getFieldListFromXMLForThisMessage(modelName, pathToSchema, implementationLanguage);
		} catch (FieldListException e) {
			this.actionEventError(methodName, e.toString());
		}					

		for (Field fld : properties ) {
			this.actionEvent(methodName, fld.toString());
		}

		VelocityContext context = new VelocityContext();


		if (implementationLanguage==ImplementationLanguage.Java) {
			if(Objects.isNull(packageNameForTransactions)){ 
				//do nothing...
			}else {
				context.put("packagename", packageNameForTransactions);
			}
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


		try {
			t.merge( context, writer );
		}catch(ResourceNotFoundException e) {
			this.actionEventError(methodName, e.toString());
		}catch(ParseErrorException e) { 
			this.actionEventError(methodName, e.toString());
		}catch(MethodInvocationException e) {
			this.actionEventError(methodName, e.toString());
		}

		retVal = writeToFile(writer, modelName, implementationLanguage); 

		return retVal;
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