package com.alignmentsystems.classgen.generator;
/******************************************************************************
 * 
 * Author          : John Greenan
 * Date            : 1st September 2020
 * Copyright       : Alignment Systems Ltd 2020
 * 
 *****************************************************************************/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

public class SimpleNamespaceContext implements NamespaceContext {

    private final Map<String, String> PREF_MAP = new HashMap<String, String>();

    /**
     * 
     * @param prefMap
     */
    public SimpleNamespaceContext(final Map<String, String> prefMap) {
        PREF_MAP.putAll(prefMap);       
    }

    /**
     * 
     */
    public String getNamespaceURI(String prefix) {
        return PREF_MAP.get(prefix);
    }

    /**
     * 
     */
    public String getPrefix(String uri) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     */
    public Iterator getPrefixes(String uri) {
        throw new UnsupportedOperationException();
    }

}