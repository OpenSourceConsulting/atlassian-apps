<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <script>
	    $( document ).ready(function() {
	    	
	    	//$("#success-alert").hide();
	    	$( "#btndlg" ).click(function( event ) {
	    		
	    		
	    		$('#success-alert').toggleClass('in');
	    		
	    		setTimeout(function() {
	    			$('#success-alert').toggleClass('in');
	    	    }, 4500);
	    		
	    		//$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
	    		    //$(this).hide();
	    		//});
	    	});
	    	
	    	$( "#btnCreate2" ).click(function( event ) {
	    		
	    		
	    		var request = $.ajax({
	    			  method : "POST",
					  url: "jira/rest/api/2/issue",
					  data: $( "form" ).serialize(),
					  dataType: "json"
	    		});
	    		
	    		request.done(function( responseJson ) {
    			    //alert(responseJson);
	    			//alert( "Request failed: " + textStatus );
    			    $('#success-alert').toggleClass('in');
	    		
		    		setTimeout(function() {
		    			$('#success-alert').toggleClass('in');
		    			
		    	    }, 4000);
		    		$("#myModal").modal("hide");
	    			
    			});
	    		
	    		request.fail(function( jqXHR, textStatus ) {
    				
    			    alert( "Request failed: " + textStatus );
    			});
	    		
	    	});
	    	
	    	$('#myModal').on('hidden.bs.modal', function (e) {
	    		$( "form" )[0].reset();
	    	});
	    	
	    	
	    	$( "#loadIssues" ).click(function( event ) {
	    	    //alert("click!!!");
	    	    $('#issuesTbl > tbody > tr').remove();
	    	    
	    		var request = $.ajax({
	    			  method : "GET",
					  url: "jira/rest/api/2/search",
					  data: {jql:"assignee=admin"},
					  dataType: "json"
	    		});
	    		/*
	    		$( 'div .divTbl' ).isLoading({
                    text:       "Loading",
                    position:   "overlay"
                });
	    		*/
	    		$(this).toggleClass('active');
	    		
	    		request.done(function( responseJson ) {
    			    //alert(responseJson);
    				
    			    //$( "div .divTbl" ).isLoading('hide');
    			    $("#loadIssues").toggleClass('active');
    			    
    			  	$.each(responseJson.issues, function( index, issue ) {
    			  		
    			  		var typeImg = '<img src="'+issue.fields.issuetype.iconUrl+'" height="16" width="16" border="0" align="absmiddle">';
    			  		var issueLink = '<a href="http://hhivaas_app01:2990/jira/browse/'+ issue.key +'">' + issue.fields.summary + '</a>';
    			  		var assignee = issue.fields.assignee.displayName;
    			  		
    			  		$('#issuesTbl > tbody:last').append('<tr><th>' + index + '</th><td>' + typeImg + '</td><td>' + issue.key + '</td><td>' + issueLink + '</td><td>' + assignee + '</td><td>' + issue.fields.status.name + '</td></tr>');
					});
	    			
    			});
    			 
    			request.fail(function( jqXHR, textStatus ) {
    				//$( "div .divTbl" ).isLoading('hide');
    				$("#loadIssues").toggleClass('active');
    				
    			    alert( "Request failed: " + textStatus );
    			});
	    	});
	    });
    </script>
      