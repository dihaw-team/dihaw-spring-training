<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
		<div style="margin: 70px;">
			<div class="error-message">
				<div class="error-title">
					<h3>Sorry, the email was not sent because of the following error:</h3>
				</div>
				<h3>${exception.message}</h3>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>