package com.alignmentsystems.classgen.generator;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import static org.w3c.dom.Node.ATTRIBUTE_NODE;
import static org.w3c.dom.Node.CDATA_SECTION_NODE;
import static org.w3c.dom.Node.COMMENT_NODE;
import static org.w3c.dom.Node.DOCUMENT_TYPE_NODE;
import static org.w3c.dom.Node.ELEMENT_NODE;
import static org.w3c.dom.Node.ENTITY_NODE;
import static org.w3c.dom.Node.ENTITY_REFERENCE_NODE;
import static org.w3c.dom.Node.NOTATION_NODE;
import static org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE;
import static org.w3c.dom.Node.TEXT_NODE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alignmentsystems.classgen.exceptions.FieldListException;
import com.alignmentsystems.classgen.exceptions.MessageListException;
import com.alignmentsystems.classgen.interfaces.Action;
import com.alignmentsystems.classgen.mapper.MapDataTypes;

public class XMLFunctions implements Action{
	private final static String stringDescription = "description";
	private final static String stringID = "id";
	private final static String stringName= "name";
	private final static String stringSemanticType = "semanticType";
	private final static String stringType = "type";
	private final static String stringInConstructorOne= "type";

	private List<Action> listeners = new ArrayList<Action>();

	public void addListener(Action toAdd) {
		listeners.add(toAdd);
	}
	private void sendActionEvent(String methodName, String  event) {
		for (Action hl : listeners)
			hl.actionEvent(methodName, event);
	}

	private void sendActionEventError(String methodName, String  event) {
		for (Action hl : listeners)
			hl.actionEventError(methodName, event);
	}


	/**
	 * 
	 * @param xsdPath
	 * @param xmlPath
	 * @return
	 */
	public boolean validateXMLSchema(String xsdPath, String xmlPath){
		final String methodName = this.getClass().getSimpleName() + "::validateXMLSchema";	

		final File xsdFile = new File(xsdPath);
		final File schemaFile = new File(xmlPath);

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = factory.newSchema(xsdFile);

			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(schemaFile));
		} catch (IOException | SAXException e) {
			this.actionEventError(methodName , e.getMessage());
			return false;
		}
		return true;
	}


	/**
	 * 
	 * @param pathToApplicationMessagesSchema
	 * @return
	 */
	public List<String> getMessageListFromXML(String pathToApplicationMessagesSchema) throws MessageListException{
		final String methodName = this.getClass().getSimpleName() + "::getMessageListFromXML";	

		final String namespaceToTarget = "sbe:messageSchema";
		final String tagNameToTarget = "sbe:message";
		final String namespaceAddress = "http://fixprotocol.io/2016/sbe";
		final String namespaceName = "sbe";
		List<Field> properties = new ArrayList<>();

		List<String> listOfMessageNames = new ArrayList<>();

		File xmlFile = new File(pathToApplicationMessagesSchema);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);

		DocumentBuilder dBuilder = null;


		try {
			dBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			this.actionEventError(methodName , e.getMessage());
			throw new MessageListException(e);
		}

		Document doc = null;

		try {
			doc = dBuilder.parse(xmlFile);

		} catch (SAXException | IOException e) {
			this.actionEventError(methodName , e.getMessage());
			throw new MessageListException(e);
		}

		if (Objects.isNull(doc)) {

		}else {

			String messageName = null;
			String messageDescription = null;
			String messageID = null;
			String messageSemanticType = null; 

			XPath xPath = XPathFactory.newInstance().newXPath();
			HashMap<String, String> prefMap = new HashMap<String, String>() {{put(namespaceName, namespaceAddress);}};
			SimpleNamespaceContext namespaces = new SimpleNamespaceContext(prefMap);
			xPath.setNamespaceContext(namespaces);

			try {
				XPathExpression expr = xPath.compile("/" + namespaceToTarget + "/" + tagNameToTarget );
				NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				if (Objects.isNull(list)) {
					this.actionEventError(methodName , expr.toString() + " returns null");
				}else {
					if(list.getLength() > 0) {
						for (int i = 0; i<list.getLength(); i++) {
							listOfMessageNames.add(list.item(i).getAttributes().getNamedItem(stringName).getNodeValue().toString());							
						}
					}else {
						this.actionEventError(methodName , expr.toString() + " list is zero length");
					}
				}
			} catch (XPathExpressionException e) {
				this.actionEventError(methodName , e.getMessage());
				throw new MessageListException(e);
			}

		}
		return listOfMessageNames;		
	}



	public List<Field> getFieldListFromXMLForThisMessage(String  modelName, String pathToApplicationMessagesSchema) throws FieldListException{
		final String methodName = this.getClass().getSimpleName() + "::getFieldListFromXMLForThisMessage";	
		final String namespaceToTarget = "sbe:messageSchema";
		final String tagNameToTarget = "sbe:message";
		final String namespaceAddress = "http://fixprotocol.io/2016/sbe";
		final String namespaceName = "sbe";
		List<Field> properties = new ArrayList<>();

		File xmlFile = new File(pathToApplicationMessagesSchema);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);

		DocumentBuilder dBuilder = null;


		try {
			dBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			this.actionEventError(methodName , e.getMessage());
			throw new FieldListException(e);
		}

		Document doc = null;

		try {
			doc = dBuilder.parse(xmlFile);
		} catch (SAXException | IOException e) {
			this.actionEventError(methodName , e.getMessage());
			throw new FieldListException(e);
		}

		if (Objects.isNull(doc)) {
			this.actionEventError(methodName , dBuilder.getClass().getSimpleName() + " returns null");

		}else {

			String messageName = null;
			String messageDescription = null;
			String messageID = null;
			String messageSemanticType = null; 

			XPath xPath = XPathFactory.newInstance().newXPath();
			HashMap<String, String> prefMap = new HashMap<String, String>() {{put(namespaceName, namespaceAddress);}};
			SimpleNamespaceContext namespaces = new SimpleNamespaceContext(prefMap);
			xPath.setNamespaceContext(namespaces);

			try {
				XPathExpression expr = xPath.compile("/" + namespaceToTarget + "/" + tagNameToTarget );
				NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				if (Objects.isNull(list)) {

				}else {
					if(list.getLength() > 0) {
						for(int i = 0 ; i<list.getLength() ; i++) {
							NamedNodeMap nameNodeMap = list.item(i).getAttributes();
							messageName = describeNode(nameNodeMap.getNamedItem(stringName), Boolean.FALSE);


							if (messageName.equalsIgnoreCase(modelName)) {

								messageDescription =  describeNode(nameNodeMap.getNamedItem(stringDescription), Boolean.FALSE);
								messageID = describeNode(nameNodeMap.getNamedItem(stringID), Boolean.FALSE);
								messageSemanticType = describeNode(nameNodeMap.getNamedItem(stringSemanticType), Boolean.FALSE);

								StringBuilder sb = new StringBuilder();
								sb.append("Show=").append(messageName)
								.append(", ").append(stringDescription).append("=").append(messageDescription)
								.append(", ").append(stringID).append("=").append(messageID)
								.append(", ").append(stringSemanticType).append("=").append(messageSemanticType)
								;


								this.actionEvent(methodName , sb.toString());


								listNodes(list.item(i),"  ", properties);	
							}else {
								//Ignore.....
								System.out.println("Ignore=" + messageName);
							}
						}
					}else {
						System.out.println("List is zero length");
					}
				}
			} catch (XPathExpressionException e) {
				System.out.println("Exception: "+e.getMessage());
			}


		}
		return properties;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private String nodeType(short type) {
		switch(type) {
		case ELEMENT_NODE:                return "Element";
		case DOCUMENT_TYPE_NODE:          return "Document type";
		case ENTITY_NODE:                 return "Entity";
		case ENTITY_REFERENCE_NODE:       return "Entity reference";
		case NOTATION_NODE:               return "Notation";
		case TEXT_NODE:                   return "Text";
		case COMMENT_NODE:                return "Comment";
		case CDATA_SECTION_NODE:          return "CDATA Section";
		case ATTRIBUTE_NODE:              return "Attribute";
		case PROCESSING_INSTRUCTION_NODE: return "Attribute";
		}
		return "Unidentified";
	}


	
	/**
	 * 
	 * @param nodeDescription
	 * @param print
	 * @return
	 */
	private String  describeNode (Node nodeDescription , Boolean print) {
		final String methodName = this.getClass().getSimpleName() + "::listNodes";	

		String retVal = null;
		if (Objects.isNull(nodeDescription)) {

		}else {
			retVal = nodeDescription.getTextContent();
			if(print) {
				this.actionEvent(methodName , retVal);
			}
		}
		return retVal;
	}



	/**
	 * 
	 * @param node
	 * @param indent
	 * @param log
	 * @param properties
	 */
	private void listNodes(Node node, String indent , List<Field> properties) {
		final String methodName = this.getClass().getSimpleName() + "::listNodes";	

		String messageDescription = null;
		String messageID = null;
		String fieldName = null;
		String messageSemanticType = null; 
		String fieldXMLType = null;


		String nodeName = node.getNodeName();
		short type = node.getNodeType();
		//System.out.println(indent+" Node: " + nodeName + " " + nodeType(type));

		if(type == TEXT_NODE){
			//System.out.println(indent+" Content is: "+((Text)node).getWholeText());
		}else if(type == ELEMENT_NODE){
			//System.out.println(indent+" Content is: "+((Text)node).getData());
		}else {
			//System.out.println(indent+" Content is: "+((Text)node).getWholeText());
		}

		NodeList list = node.getChildNodes();       
		if(list.getLength() > 0) {                  
			//System.out.println(indent+" Child Nodes of " + nodeName + " are:");
			for(int i = 0 ; i<list.getLength() ; i++) {
				listNodes(list.item(i),indent+"  ", properties);     
			}
		}else {
			NamedNodeMap nameNodeMap = node.getAttributes();
			if (Objects.isNull(nameNodeMap)) {
				//System.out.println("No children");				
			}else {
				messageDescription =  describeNode(nameNodeMap.getNamedItem(stringDescription) , Boolean.FALSE);
				messageID = describeNode(nameNodeMap.getNamedItem(stringID) , Boolean.FALSE);
				messageSemanticType = describeNode(nameNodeMap.getNamedItem(stringSemanticType) , Boolean.FALSE);
				fieldName = describeNode(nameNodeMap.getNamedItem(stringName) , Boolean.FALSE);
				fieldXMLType = describeNode(nameNodeMap.getNamedItem(stringType) , Boolean.FALSE);

				MapDataTypes mapX = new MapDataTypes(); 

				String fieldTypeJava = mapX.getJavaTypeNameForXMLTypeName(fieldXMLType);


				//String fieldName, String fieldType, Boolean inConstructorOne, String description, String semanticType
				Field addThisField = new Field(
						fieldName
						, fieldXMLType
						, fieldTypeJava
						, Boolean.TRUE
						, messageDescription
						, messageSemanticType
						);

				properties.add(addThisField);

				StringBuilder sb = new StringBuilder();
				sb
				.append("[Field]") 
				.append(", stringDescription=").append(messageDescription) 
				.append(", stringID=").append(messageID)					
				.append(", fieldName=").append(fieldName)
				.append(", fieldXMLType=").append(fieldXMLType)
				.append(",  fieldTypeJava=").append(fieldTypeJava)
				.append(", stringSemanticType=").append(messageSemanticType)										
				;


				this.actionEvent(methodName , sb.toString());
			}
		}         
	}
	@Override
	public void actionEvent(String methodName, String event) {
		this.sendActionEvent(methodName, event);		
	}

	@Override
	public void actionEventError(String methodName, String event) {
		this.sendActionEventError(methodName, event);		
	}

}