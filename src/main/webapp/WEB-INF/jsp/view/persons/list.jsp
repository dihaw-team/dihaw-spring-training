<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
		<div style="padding: 60px 10px 0;">
			<div class="title">
				<h3><spring:message code="persons-list" /></h3>
			</div>
			
			<center>
			
			<c:choose>
				<c:when test="${fn:length(persons) eq 0}">
					<div id="warning">
						<spring:message code="empty-persons-list" />
					</div>					
				</c:when>
				
				<c:otherwise>
					<table style="width: 90%" class="reference">
						<tbody>
							<tr>
								<th><spring:message code="index" /></th>
								<th><spring:message code="name" /></th>
								<th><spring:message code="age" /></th>
								<th><spring:message code="email" /></th>
								<th><spring:message code="action" /></th>
							</tr>
							<c:forEach var="person" items="${persons}" varStatus="loopCounter">
								<tr>
									<td><c:out value="${loopCounter.count}" /></td>
									<td><c:out value="${person.name}" /></td>
									<td><c:out value="${person.age}" /></td>
									<td><c:out value="${person.email}" /></td>
									<td>
										<a href="remove?index=${loopCounter.count-1}" >
											<spring:message code="remove" />
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>