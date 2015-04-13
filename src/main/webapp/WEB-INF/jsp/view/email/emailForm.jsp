<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	    <div style="padding:60px 10px 0;">
	    	<div class="title">
	    		<h3><spring:message code="mail.title" /></h3>
	    	</div>
	    	<div class="section">
		    	<form method="post" action="email/send">
		    		<div class="user">
		    			<ul>
							<li class="huge">
								<label><strong><spring:message code="mail.to" /></strong></label>
								<input type="text" name="recipient" size="65" />
							</li>
							<li class="huge">
								<label><strong><spring:message code="mail.subject" /></strong></label>
								<input type="text" name="subject" size="65" />
							</li>							
							<li class="huge">
								<label><strong><spring:message code="mail.message" /></strong></label>
								<textarea cols="45" rows="8" name="message"></textarea>
							</li>							
						</ul>
		    		</div>
		    		<div class="huge-btn">
		    			<input type="submit" class="btn" value="<spring:message code="mail.send" />" />
		    		</div>
				</form>
			</div>
	    </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
