<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<spring:message code="timePattern" var="timePattern" />
<spring:message code="datePattern" var="datePattern" />

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	    <div style="padding: 60px 10px 0;">
			<div class="title">
				<h3><spring:message code="user.list" /></h3>
			</div>
			
			<div class="pagination">
			    <ul>
			        <c:forEach var="i" begin="0" end="${result.totalPages -1}">
			            <c:url var="pageUrl" value="/users/list?page=${i}" />
			            <c:choose>
			                <c:when test="${i == result.number}">
			                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:when>
			                <c:otherwise>
			                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			    </ul>
			</div>
			
			<div class="section">
				<ul>
					<li class="section-head-20-left"><spring:message code="id" /></li>
					<li class="section-head-20"><spring:message code="firstName" /></li>
					<li class="section-head-20"><spring:message code="lastName" /></li>
					
					<li class="section-head-10"><spring:message code="gender" /></li>
					<li class="section-head-10"><spring:message code="city" /></li>
					
					<li class="section-head-10"><spring:message code="edit" /></li>
					<li class="section-head-10-right"><spring:message code="delete" /></li>
				</ul>	
				<c:forEach var="user" items="${result.content}" >
					<ul class="section-body">
					
						<li class="section-body-20">${user.id}</li>
						<li class="section-body-20">${user.firstName}</li>
						<li class="section-body-20">${user.lastName}</li>
						
						<li class="section-body-10">${user.gender}</li>
						<li class="section-body-10">${user.city.cityName}</li>
						
						<li class="section-body-10">
							<a href="edit?id=${user.id}" >
								<img src="<c:url value="/static/images/action/edit-icon.png" />" style="max-height: 22px; margin-top: 5px;">
							</a>						
						</li>
						
						<li class="section-body-10">
							<a id="${stat.index}" href="#delete" class="sets" onclick="switchDetails(${user.id})">
								<img src="<c:url value="/static/images/action/delete-icon.png" />" style="max-height: 22px; margin-top: 5px;">
							</a>						
						</li>
						
					</ul>
				</c:forEach>
			</div>
					
			<div class="btn-div">
				<a href="add" class="btn"><spring:message code="add-new-user" /></a>
			</div>
	
		</div>
		
		<div id="dialog-container" style="display:none">
			<div class="details">
				<div class="details-container" >
					<h3><spring:message code="question" /></h3>
					
					<div style=" margin: 20px 0 15px;">
						<a id="deleteAction" href="delete?id=" class="btn"><spring:message code="yes" /></a>
						<a href="#" class="btn" onclick="closeDetails()"><spring:message code="cancel" /></a>
					</div>
				</div>
			</div>
		</div>
		
    </tiles:putAttribute>
</tiles:insertDefinition>
