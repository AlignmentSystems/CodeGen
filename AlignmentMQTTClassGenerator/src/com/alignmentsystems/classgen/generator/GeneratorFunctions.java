package com.alignmentsystems.classgen.generator;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/
import java.util.Calendar;

import com.alignmentsystems.classgen.enumerations.ImplementationLanguage;


public class GeneratorFunctions {
	final static String underscore = "_";
	final static String javaComment = "//";
	final static String cppComment = "//";
	final static String qComment = "/";
	final static String csharpComment = "//";
	final static String pythonComment = "#";
	
	/**
	 * 
	 * @param existingComment
	 * @param implementationLanguage
	 * @return
	 */
	public static String getOneLineComment(String existingComment, ImplementationLanguage implementationLanguage) {
		String oneLineComment = null;
		

		if (implementationLanguage==ImplementationLanguage.Java) {
			oneLineComment = javaComment + existingComment ;
		}else if (implementationLanguage==ImplementationLanguage.Cpp) {
			oneLineComment = cppComment + existingComment ;
		}else if (implementationLanguage==ImplementationLanguage.CSharp) {
			oneLineComment = csharpComment + existingComment ;
		}else if (implementationLanguage==ImplementationLanguage.Python) {
			oneLineComment = pythonComment + existingComment ;
		}else if (implementationLanguage==ImplementationLanguage.q) {
			oneLineComment = qComment + existingComment ;
		}


		return oneLineComment;

	}

	/**
	 * 
	 * @return
	 */
	public static String getCreationDate() {

		final Calendar now = Calendar.getInstance();
		final Integer thisYear = now.get(Calendar.YEAR);
		final Integer thisMonth = now.get(Calendar.MONTH)+1;
		final Integer thisDay = now.get(Calendar.DAY_OF_MONTH);
		final Integer thisHour = now.get(Calendar.HOUR_OF_DAY);
		final Integer thisMinute = now.get(Calendar.MINUTE);
		final Integer thisSecond = now.get(Calendar.SECOND);

		return Integer.toString(thisYear)
				+ underscore
				+ String.format("%02d", thisMonth)
				+ underscore
				+ String.format("%02d", thisDay)
				+ underscore
				+ String.format("%02d", thisHour)
				+ underscore
				+ String.format("%02d", thisMinute)
				+ underscore
				+ String.format("%02d", thisSecond);
	}

	
	/**
	 * 
	 * @param existingRoot
	 * @return
	 */
	public static String getDateValue(String existingRoot) {

		final String dot = ".";

		final String formattedName =
				existingRoot
				+ dot
				+ underscore
				+ getCreationDate();
		return  formattedName;
	}
}
