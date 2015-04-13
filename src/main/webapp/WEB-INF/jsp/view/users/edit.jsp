<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="timePattern" var="timePattern" />
<spring:message code="datePattern" var="datePattern" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div style="padding: 50px 0 0;">
			<div class="title">
				<h3><spring:message code="user.edit" /></h3>
			</div>
			<div class="section">
			
				<c:choose>
					<c:when test="${status eq 'SUCCESS'}">
						<div id="main-message">
							<div id="form-container">
								<div id="success">
									<spring:message code="success" />
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${status eq 'WRONG_DATA'}">
						<div id="main-message">
							<div id="form-container">
								<div id="error"><spring:message code="wrong-data" /></div>
							</div>
						</div>
					</c:when>
				</c:choose>				
			
				<form:form method="post" action="/users/do-edit" modelAttribute="user">
					<div class="section">
						<ul>
							<li class="huge">
								<form:label path="firstName"><strong><spring:message code="firstName" /></strong></form:label> 
								<form:input path="firstName" name="firstName" />
								<form:errors path="firstName" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="lastName"><strong><spring:message code="lastName" /></strong></form:label> 
								<form:input path="lastName" name="lastName" />
								<form:errors path="lastName" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="gender"><strong><spring:message code="gender" /></strong></form:label>
								<spring:bind path="gender">
									<c:forEach items='${genderList}' var='genderName'>
										<c:choose>
											<c:when test="${genderName eq user.gender}">
												<input type="radio" name="gender" value="${genderName}"	checked="checked">${genderName}
											</c:when>
											<c:otherwise>
												<input type="radio" name="gender" value="${genderName}">${genderName}
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</spring:bind>
								<form:errors path="gender" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="city"><strong><spring:message code="city" /></strong></form:label>
								<spring:bind path="city">
									<select name="city">
										<c:forEach items='${cityList}' var='city'>
											<c:choose>
												<c:when test="${city.cityName eq user.city.cityName}">
													<option value="${city.cityName}" selected="true">${city.cityName}</option>
												</c:when>
												<c:otherwise>
													<option value="${city.cityName}">${city.cityName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</spring:bind>
								<form:errors path="city" element="label" cssClass="error"/>
							</li>				
							
						</ul>
					</div>
					<ul>
						<li class="huge-btn">
							<button type="submit" name="submit"><spring:message code="save" /></button>
							<a href="list" class="btn"><spring:message code="cancel" /></a>
						</li>
					</ul>
					<form:hidden path="id" value="${user.id}" />
				</form:form>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>