/**
 * 
 */
package com.atlassian.tutorial.myweb.jira;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class Issue {
	
	private Map<String, Object> fields = new HashMap<String, Object>();

	/**
	 * 
	 */
	public Issue(String pkey, String summary, String desc, String itype) {
		
		Map<String, String> prjMap = new HashMap<String, String>();
		prjMap.put("key", pkey);
		
		Map<String, String> typeMap = new HashMap<String, String>();
		typeMap.put("name", itype);
		
		fields.put("project", prjMap);
		fields.put("summary", summary);
		fields.put("description", desc);
		fields.put("issuetype", typeMap);
		
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}
}
