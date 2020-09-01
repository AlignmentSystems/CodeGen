package com.alignmentsystems.classgen.generator;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import java.util.Locale;

public class Field {
    private String fieldName;
    private String fieldTypeJava;
    private String fieldTypeXML;
    private String description;
    private Boolean inConstructorOne;
    private String semanticType;


/**
 * 
 * @param fieldName
 * @param fieldTypeXML
 * @param fieldTypeJava
 * @param inConstructorOne
 * @param description
 * @param semanticType
 */
    public Field(String fieldName, String fieldTypeXML, String fieldTypeJava,  Boolean inConstructorOne, String description, String semanticType) {
        super();
        this.fieldName = fieldName;
        this.fieldTypeXML = fieldTypeXML;
        this.inConstructorOne = inConstructorOne;
        this.description = description;
        this.semanticType = semanticType;
        this.fieldTypeJava = fieldTypeJava ; 
    }

    /**
     * 
     * @return
     */
    public String getProperFieldName() {
        String firstLetter = this.fieldName.substring(0, 1).toUpperCase(Locale.ROOT);
        String theRest = this.fieldName.substring(1);
    	return  firstLetter + theRest; 
    	    	
    }

    /**
     * 
     * @return
     */
    public String getFieldNameLowercaseFirstLetter() {
        String firstLetter = this.fieldName.substring(0, 1).toLowerCase(Locale.ROOT);
        String theRest = this.fieldName.substring(1);
    	return  firstLetter + theRest; 
    }

    
    /**
     * 
     * @return
     */
    public String getDescription() {
    	return this.description;
    }
    
    /**
     * 
     * @return
     */
    public String getSemanticType() {
    	return this.semanticType;
    }
    
    /**
     * 
     * @return
     */
    public Boolean isInConstructorOne() {
    	return this.inConstructorOne;
    }
      
    
    public String getFieldTypeJava() {
        return this.fieldTypeJava;
    }
    
    /**
     * 
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }
    
    /**
     * 
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    /**
     * 
     * @return
     */
    public String getFieldTypeXML() {
        return fieldTypeXML;
    }
    
    /**
     * 
     * @param fieldType
     */
    public void setFieldTypeJava(String fieldTypeJava) {
        this.fieldTypeJava = fieldTypeJava;
    }

	@Override
	public String toString() {
		return "Field [fieldName=" + fieldName 
				+ ", fieldTypeJava=" + fieldTypeJava
				+ ", fieldTypeXML=" + fieldTypeXML
				+ ", description=" + description
				+ ", inConstructorOne=" + inConstructorOne 
				+ ", semanticType=" + semanticType 
				+ "]";
	}
}