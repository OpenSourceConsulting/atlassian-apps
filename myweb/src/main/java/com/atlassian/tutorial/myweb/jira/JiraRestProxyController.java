/**
 * 
 */
package com.atlassian.tutorial.myweb.jira;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Bongjin Kwon
 *
 */
@Controller
public class JiraRestProxyController {
	
	
	//private HttpHost jiraHost = new HttpHost("localhost", 2990, "http");
	private HttpHost jiraHost = new HttpHost("hhivaas_app01", 2990, "http");
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value = {"/"}, method=RequestMethod.GET)
    public String home() {
        return "page.home";
    }
	
	@RequestMapping("/jira/rest/api/2/issue")
    @ResponseBody
    public String create(@RequestParam("pkey") String pkey, @RequestParam("summary") String summary, @RequestParam("desc") String desc, @RequestParam("itype") String itype ) throws Exception {
		
		Issue issue = new Issue(pkey, summary, desc, itype);
		String json = om.writeValueAsString(issue);
		
		System.out.println(json);
		
		HttpUriRequest httpreq = RequestBuilder.post()
                .setUri(new URI(jiraHost.toURI() +"/jira/rest/api/2/issue"))
                .setHeader("Content-Type", "application/json;charset=UTF-8")
                .setEntity(new StringEntity(json, "UTF-8")) // set json request body.
                .build();
		
		return callAPI(httpreq);
	}

	@RequestMapping("/jira/rest/api/2/search")
    @ResponseBody
    public String search(@RequestParam("jql") String jql ) throws Exception {
		
		
        HttpUriRequest httpget = RequestBuilder.get()
                .setUri(new URI(jiraHost.toURI() +"/jira/rest/api/2/search"))
                .addParameter("jql", jql)
                .build();

		
        return callAPI(httpget);
    }
	
	private String callAPI(HttpUriRequest request) throws Exception {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(jiraHost.getHostName(), jiraHost.getPort()),
                new UsernamePasswordCredentials("admin", "admin"));
        
        AuthCache authCache = new BasicAuthCache();
        authCache.put(jiraHost, new BasicScheme());
        
        // Add AuthCache to the execution context
        final HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
        
        HttpClient httpclient = HttpClientBuilder.create().build();
        

        System.out.println("Executing request " + request.getRequestLine());
        HttpResponse response = httpclient.execute(request, context);

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        String resJson = EntityUtils.toString(response.getEntity());
        System.out.println(resJson);
        
        return resJson;
	}
	
}
