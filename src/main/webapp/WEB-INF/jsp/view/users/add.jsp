<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="timePattern" var="timePattern" />
<spring:message code="datePattern" var="datePattern" />

<tiles:insertDefinition name="defaultTemplate">
   <tiles:putAttribute name="body">
		<div style="padding: 50px 0 0;">
			<div class="title">
				<h3><spring:message code="registrationForm" /></h3>
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
			
				<form:form method="post" action="/users/do-add" modelAttribute="user">
					<div class="user">
						<ul>
							<li class="huge">
								<form:label path="firstName"><strong><spring:message code="firstName" /></strong></form:label> 
								<form:input path="firstName" name="firstName" />
								<form:errors path="firstName" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="username"><strong><spring:message code="username" /></strong></form:label> 
								<form:input path="username" name="username" />
								<form:errors path="username" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="email"><strong><spring:message code="email" /></strong></form:label> 
								<form:input path="email" name="email" />
								<form:errors path="email" element="label" cssClass="error"/>
							</li>
							<li class="huge">
								<form:label path="password"><strong><spring:message code="password" /></strong></form:label> 
								<form:input path="password" name="password" />
								<form:errors path="password" element="label" cssClass="error"/>
							</li>
							<li class="radio">
								<form:label path="gender"><strong><spring:message code="gender" /></strong></form:label>
								<div class="radio"> 
									<form:radiobuttons path="gender" items="${genderList}" />
									<form:errors path="gender" element="label" cssClass="error"/>
								</div>
							</li>
							<li class="radio">
								<form:label path="status"><strong><spring:message code="status" /></strong></form:label>
								<div class="radio"> 
									<form:radiobuttons path="status" items="${statusList}" />
									<form:errors path="status" element="label" cssClass="error"/>
								</div>
							</li>
							
							<%--li class="huge">
								<form:label path="userRole"><strong><spring:message code="userRole" /></strong></form:label> 
								<spring:bind path="userRole">
									<select name="userRole">
										<c:forEach items="${roleList}" var="item">
											<option value="${item}">${item}
												spring:message code="user-role.${item}" />
											</option>
										</c:forEach>
									</select>
								</spring:bind>								
							</li --%>
							
							<li class="huge">
								<form:label path="city"><strong><spring:message code="city" /></strong></form:label> 
								<spring:bind path="city">
									<select name="city">
										<c:forEach items="${cityList}" var="city">
											<option value="${city.cityName}">${city.cityName}</option>
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
				</form:form>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>