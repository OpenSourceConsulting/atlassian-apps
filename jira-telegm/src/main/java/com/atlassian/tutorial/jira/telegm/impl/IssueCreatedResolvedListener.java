/**
 * 
 */
package com.atlassian.tutorial.jira.telegm.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

/**
 * @author BongJin Kwon
 *
 */
@ExportAsService ({IssueCreatedResolvedListener.class})
@Named ("eventListener")
public class IssueCreatedResolvedListener implements InitializingBean, DisposableBean{
	private static final Logger LOGGER = LoggerFactory.getLogger(IssueCreatedResolvedListener.class);
	
	@ComponentImport
	private final EventPublisher eventPublisher;
	
	@Inject
	public IssueCreatedResolvedListener(final EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	 /**
     * Called when the plugin has been enabled.
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // register ourselves with the EventPublisher
        eventPublisher.register(this);
    }

    /**
     * Called when the plugin is being disabled or removed.
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        // unregister ourselves with the EventPublisher
        eventPublisher.unregister(this);
    }
	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	   Long eventTypeId = issueEvent.getEventTypeId();
	   Issue issue = issueEvent.getIssue();
	   
	   String eventStr = "";
	   LOGGER.info("onIssueEvent ===================== " + issue.getKey());
	   if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
		   
		   eventStr = "created.";
		   LOGGER.info("Issue {} has been created at {}.", issue.getKey(), issue.getCreated());
		   
	   } else if (eventTypeId.equals(EventType.ISSUE_RESOLVED_ID)) {
		   
		   eventStr = "resolved.";
		   LOGGER.info("Issue {} has been resolved at {}.", issue.getKey(), issue.getResolutionDate());
		   
	   } else if (eventTypeId.equals(EventType.ISSUE_CLOSED_ID)) {
		   
		   eventStr = "closed.";
		   LOGGER.info("Issue {} has been closed at {}.", issue.getKey(), issue.getUpdated());
	   }
	   
	   sendTelegm(issue, eventStr);
	}
	
	public static String sendTelegm(Issue issue, String eventStr){
		
		List<String> cmds = new ArrayList<String>();
		cmds.add("/home/osc/tg/send_msg.sh");
		cmds.add("Àü°©ÀÏ");
		//cmds.add("<strong>Jira :</strong> <a href=\"http://hhivaas_app01:2990/jira/browse/"+issue.getKey()+"\">" + issue.getKey() +" " + issue.getSummary() + "</a> issue " + eventStr);
		cmds.add("Jira : " + issue.getKey() +" " + issue.getSummary() + " issue " + eventStr + " http://hhivaas_app01:2990/jira/browse/" + issue.getKey());
		
		
		ProcessBuilder bld = new ProcessBuilder(cmds);
		BufferedReader br = null;
		Process p = null;
		String result = null;
		try {
			
			p = bld.start();
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line;

			while ((line = br.readLine()) != null) {
				LOGGER.info(line);
			}
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if(br != null){
				try{ br.close(); }catch(IOException e){}
			}
			
			if(p != null){
				p.destroy();
			}
		}
		
		return result;
	}
}
