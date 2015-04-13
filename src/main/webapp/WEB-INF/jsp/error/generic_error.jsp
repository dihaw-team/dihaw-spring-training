<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
		<div style="margin: 70px;">
			<div class="error-message">
				<div class="error-title">
					<h3><spring:message code="error.title" /></h3>
				</div>
				<spring:message code="systemErrorCode"/><br/>
				<span><spring:message code="tryLater"/></span>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>
